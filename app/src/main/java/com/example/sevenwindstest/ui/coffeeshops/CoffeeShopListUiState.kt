package com.example.sevenwindstest.ui.coffeeshops

import android.location.Location
import com.example.sevenwindstest.domain.model.CoffeeShopWithDistance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class CoffeeShopListUiState(
    val isLoading: Boolean = false,
    val coffeeShopsWithDistance: List<CoffeeShopWithDistance>? = emptyList(),
    val errorMessage: String? = null,
    val userLocation: Location? = null,
    val locationAvailable: Boolean = false
)

fun MutableStateFlow<CoffeeShopListUiState>.setLoading(isLoading: Boolean) {
    update { it.copy(isLoading = isLoading) }
}

fun MutableStateFlow<CoffeeShopListUiState>.setCoffeeShopWithDistList(coffeeShopsWithDistance: List<CoffeeShopWithDistance>?) {
    update { it.copy(coffeeShopsWithDistance = coffeeShopsWithDistance) }
}

fun MutableStateFlow<CoffeeShopListUiState>.setErrorMessage(errorMessage: String?) {
    update { it.copy(errorMessage = errorMessage) }
}

fun MutableStateFlow<CoffeeShopListUiState>.setUserLocation(location: Location?) {
    update { it.copy(userLocation = location) }
}

fun MutableStateFlow<CoffeeShopListUiState>.setLocationAvailable(locationAvailable: Boolean) {
    update { it.copy(locationAvailable = locationAvailable) }
}