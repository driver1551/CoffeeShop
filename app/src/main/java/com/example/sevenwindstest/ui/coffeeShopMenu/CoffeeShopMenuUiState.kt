package com.example.sevenwindstest.ui.coffeeShopMenu

import com.example.sevenwindstest.data.dto.CoffeeShopMenu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class CoffeeShopMenuUiState(
    val isLoading: Boolean = false,
    val coffeeShopId: Long? = null,
    val errorMessage: String? = null,
    val coffeeShopMenuList: List<CoffeeShopMenu>? = emptyList()
)

fun MutableStateFlow<CoffeeShopMenuUiState>.setLoading(isLoading: Boolean) {
    update { it.copy(isLoading = isLoading) }
}

fun MutableStateFlow<CoffeeShopMenuUiState>.setCoffeeShopMenuList(coffeeShopMenuList: List<CoffeeShopMenu>?) {
    update { it.copy(coffeeShopMenuList = coffeeShopMenuList) }
}

fun MutableStateFlow<CoffeeShopMenuUiState>.setErrorMessage(errorMessage: String?) {
    update { it.copy(errorMessage = errorMessage) }
}