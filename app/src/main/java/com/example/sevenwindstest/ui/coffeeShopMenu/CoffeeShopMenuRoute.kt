package com.example.sevenwindstest.ui.coffeeShopMenu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sevenwindstest.ui.shoppingCart.ShoppingCartViewModel

@Composable
fun CoffeeShopMenuRoute(
    onNavigateToShoppingCart: () -> Unit
) {
    val viewModel: CoffeeShopMenuViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val shoppingCartViewModel: ShoppingCartViewModel = hiltViewModel()
    val cartState by shoppingCartViewModel.uiState.collectAsState()

    val navigateToShoppingCart by rememberUpdatedState(onNavigateToShoppingCart)

    LaunchedEffect(Unit) {
        viewModel.navigateToShoppingCart.collect {
            navigateToShoppingCart()
        }
    }

    CoffeeShopMenuScreen(
        uiState = uiState,
        cartState = cartState,
        onToShoppingCartClick = viewModel::onToShoppingCartClick,
        onAddItem = shoppingCartViewModel::addItem,
        onRemoveItem = shoppingCartViewModel::removeItem
    )
}
