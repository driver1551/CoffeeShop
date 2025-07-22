package com.example.sevenwindstest.ui.coffeeshops

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindstest.data.repository.LocationClient
import com.example.sevenwindstest.domain.model.CoffeeShopWithDistance
import com.example.sevenwindstest.domain.usecase.GetCoffeeShopsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeShopListViewModel @Inject constructor(
    private val getCoffeeShopsUseCase: GetCoffeeShopsUseCase,
    private val locationClient: LocationClient
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoffeeShopListUiState())
    val uiState = _uiState.asStateFlow()

    private val _navigateToCoffeeShop = MutableSharedFlow<Int>()
    val navigateToCoffeeShop: SharedFlow<Int> = _navigateToCoffeeShop.asSharedFlow()

    private val _navigateToMap = MutableSharedFlow<Unit>()
    val navigateToMap: SharedFlow<Unit> = _navigateToMap.asSharedFlow()

    init {
        viewModelScope.launch {
            _uiState.setLoading(true)

            val userLocation = locationClient.getLastKnownLocation()

            val result = getCoffeeShopsUseCase.invoke()

            if (result.isSuccess) {
                val coffeeShops = result.getOrNull() ?: emptyList()
                val coffeeShopsWithDistance = userLocation?.let { userLocation ->
                    // Для каждой кофейни рассчитываем расстояние до пользователя в обертке CoffeeShopWithDistance
                    coffeeShops.map { coffeeShop ->
                        CoffeeShopWithDistance(
                            coffeeShop = coffeeShop,
                            distanceMeters = calculateDistanceMeters(
                                userLocation.latitude,
                                userLocation.longitude,
                                coffeeShop.point.latitude,
                                coffeeShop.point.longitude
                            )
                        )
                        // Сортируем кофейни по расстоянию от пользователя
                    }.sortedBy { it.distanceMeters }
                } ?:
                // // Если местоположение пользователя недоступно, то всем кофейням присваиваем максимальное расстояние
                coffeeShops.map {
                    CoffeeShopWithDistance(it, Float.MAX_VALUE)
                }

                _uiState.setLoading(false)
                _uiState.setCoffeeShopWithDistList(coffeeShopsWithDistance)
                _uiState.setUserLocation(userLocation)
                _uiState.setErrorMessage(null)
            } else {
                _uiState.setLoading(false)
                _uiState.setErrorMessage("Что-то пошло не так ...")
            }

            if (userLocation == null) {
                _uiState.setLocationAvailable(false)
            } else {
                _uiState.setLocationAvailable(true)
            }
        }
    }

    fun calculateDistanceMeters(
        startLat: Double, startLon: Double,
        endLat: Double, endLon: Double
    ): Float {
        val results = FloatArray(1)
        // Записываем результаты в массив, т.к. метод возвращает void
        Location.distanceBetween(startLat, startLon, endLat, endLon, results)
        return results[0]
    }

    fun onCoffeeShopClick(id: Int) {
        viewModelScope.launch {
            _navigateToCoffeeShop.emit(id)
        }
    }

    fun onMapClick() {
        viewModelScope.launch {
            _navigateToMap.emit(Unit)
        }
    }
}