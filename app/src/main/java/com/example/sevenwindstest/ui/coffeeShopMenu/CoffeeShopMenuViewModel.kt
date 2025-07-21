package com.example.sevenwindstest.ui.coffeeShopMenu

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindstest.domain.usecase.GetCoffeeShopMenuByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeShopMenuViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCoffeeShopMenuByIdUseCase: GetCoffeeShopMenuByIdUseCase
) : ViewModel() {
    private val coffeeShopId: Long = checkNotNull(savedStateHandle["id"])

    private val _uiState = MutableStateFlow(CoffeeShopMenuUiState(coffeeShopId = coffeeShopId))
    val uiState = _uiState.asStateFlow()

    private val _navigateToShoppingCart = MutableSharedFlow<Unit>()
    val navigateToShoppingCart: SharedFlow<Unit> = _navigateToShoppingCart.asSharedFlow()

    init {
        viewModelScope.launch {
            _uiState.setLoading(true)
            val request = getCoffeeShopMenuByIdUseCase.invoke(id = coffeeShopId)
            if (request.isSuccess) {
                _uiState.setCoffeeShopMenuList(request.getOrNull()!!)
            } else {
                _uiState.setErrorMessage("Что-то пошло не так ...")
            }
            _uiState.setLoading(false)
        }
    }

    fun onToShoppingCartClick() {
        viewModelScope.launch {
            _navigateToShoppingCart.emit(Unit)
        }
    }
}