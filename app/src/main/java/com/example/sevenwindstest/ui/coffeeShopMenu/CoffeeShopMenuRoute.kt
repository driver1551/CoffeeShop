package com.example.sevenwindstest.ui.coffeeShopMenu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CoffeeShopMenuRoute() {
    val viewModel: CoffeeShopMenuViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    CoffeeShopMenuScreen(
        uiState = uiState
    )
}