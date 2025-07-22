package com.example.sevenwindstest.ui.coffeeshops

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CoffeeShopListRoute(
    onNavigateToCoffeeShop: (Int) -> Unit,
    onNavigateToMap: () -> Unit,
    navController: NavController
) {
    val viewModel: CoffeeShopListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val navigateToCoffeeShop by rememberUpdatedState(onNavigateToCoffeeShop)
    val navigateToMap by rememberUpdatedState(onNavigateToMap)

    LaunchedEffect(Unit) {
        viewModel.navigateToCoffeeShop.collect { id ->
            navigateToCoffeeShop(id)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navigateToMap.collect {
            navigateToMap()
        }
    }

    CoffeeShopListScreen(
        uiState = uiState,
        onCoffeeShopClick = viewModel::onCoffeeShopClick,
        onBackClick = { navController.popBackStack() },
        onMapClick = viewModel::onMapClick
    )
}