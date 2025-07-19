package com.example.sevenwindstest.ui.coffeeshops

import com.example.sevenwindstest.data.dto.CoffeeShop
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class CoffeeShopListUiState(
    val isLoading: Boolean = false,
    val coffeeShopList: List<CoffeeShop>? = emptyList(),
    val errorMessage: String? = null
)

fun MutableStateFlow<CoffeeShopListUiState>.setLoading(isLoading: Boolean) {
    update { it.copy(isLoading = isLoading) }
}

fun MutableStateFlow<CoffeeShopListUiState>.setCoffeeShopList(coffeeShopList: List<CoffeeShop>?) {
    update { it.copy(coffeeShopList = coffeeShopList) }
}

fun MutableStateFlow<CoffeeShopListUiState>.setErrorMessage(errorMessage: String) {
    update { it.copy(errorMessage = errorMessage) }
}