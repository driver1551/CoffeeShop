package com.example.sevenwindstest.ui.coffeeshops

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CoffeeShopListRoute(
    onNavigateToCoffeeShop: (Int) -> Unit
) {
    val viewModel: CoffeeShopListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val navigateToCoffeeShop by rememberUpdatedState(onNavigateToCoffeeShop)

    LaunchedEffect(Unit) {
        viewModel.navigateToCoffeeShop.collect { id ->
            navigateToCoffeeShop(id)
        }
    }

    CoffeeShopListScreen(
        uiState = uiState,
        onCoffeeShopClick = viewModel::onCoffeeShopClick
    )
}