package com.example.sevenwindstest.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sevenwindstest.navigation.Screen
import com.example.sevenwindstest.ui.coffeeshops.CoffeeShopListViewModel
import com.example.sevenwindstest.ui.shoppingCart.ShoppingCartViewModel

@Composable
fun CoffeeShopMapRoute(
    navController: NavController
){
    val parentEntry = remember(navController.currentBackStackEntry) {
        navController.getBackStackEntry(Screen.CoffeeShopList.route)
    }
    val viewModel: CoffeeShopListViewModel = hiltViewModel(parentEntry)

    val uiState by viewModel.uiState.collectAsState()

    uiState.coffeeShopsWithDistance?.let { shops ->
        CoffeeShopsMapScreen(
            coffeeShops = shops,
            onBackClick = { navController.popBackStack() },
            onCoffeeShopClick = viewModel::onCoffeeShopClick
        )
    } ?: run {
        // пока список не загружен
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
