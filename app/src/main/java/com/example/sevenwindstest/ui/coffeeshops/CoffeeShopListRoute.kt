package com.example.sevenwindstest.ui.coffeeshops

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CoffeeShopRoute(
    onNavigateToCoffeeShop: () -> Unit
) {
    val viewModel: CoffeeShopListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val navigateToCoffeeShop by rememberUpdatedState(onNavigateToCoffeeShop)

    LaunchedEffect(Unit) {
        viewModel.navigateToCoffeeShop.collect {
            navigateToCoffeeShop()
        }
    }

    CoffeeShopListScreen(
        uiState = uiState,
        onCoffeeShopClick = viewModel::onCoffeeShopClick
    )
}