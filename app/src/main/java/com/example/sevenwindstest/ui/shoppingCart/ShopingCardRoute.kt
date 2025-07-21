package com.example.sevenwindstest.ui.shoppingCart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sevenwindstest.navigation.Screen

@Composable
fun ShoppingCartRoute(navController: NavHostController) {
    val parentEntry = remember(navController.currentBackStackEntry) {
        navController.getBackStackEntry(Screen.CoffeeShop.route)
    }

    val viewModel: ShoppingCartViewModel = hiltViewModel(parentEntry)

    val uiState by viewModel.uiState.collectAsState()

    ShoppingCartScreen(
        cartState = uiState,
        onAddItem = viewModel::addItem,
        onRemoveItem = viewModel::removeItem,
        onBackClick = {navController.popBackStack()}
    )
}



