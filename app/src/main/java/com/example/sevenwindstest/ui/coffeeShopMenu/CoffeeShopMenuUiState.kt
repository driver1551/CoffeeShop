package com.example.sevenwindstest.ui.coffeeShopMenu

data class CoffeeShopMenuUiState(
    val isLoading: Boolean = false,
    val coffeeShopId: Int? = null,
    val errorMessage: String? = null
)