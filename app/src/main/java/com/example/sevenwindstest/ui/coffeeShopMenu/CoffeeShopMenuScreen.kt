package com.example.sevenwindstest.ui.coffeeShopMenu

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CoffeeShopMenuScreen(
    uiState: CoffeeShopMenuUiState
) {
    Text(uiState.coffeeShopId.toString())
}