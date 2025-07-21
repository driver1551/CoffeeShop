package com.example.sevenwindstest.ui.shoppingCart

import com.example.sevenwindstest.data.dto.CartItem

data class ShoppingCartUiState(
    val items: List<CartItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)