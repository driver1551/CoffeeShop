package com.example.sevenwindstest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sevenwindstest.ui.coffeeShopMenu.CoffeeShopMenuRoute
import com.example.sevenwindstest.ui.coffeeshops.CoffeeShopListRoute
import com.example.sevenwindstest.ui.login.LoginRoute
import com.example.sevenwindstest.ui.map.CoffeeShopMapRoute
import com.example.sevenwindstest.ui.registration.RegistrationRoute
import com.example.sevenwindstest.ui.shoppingCart.ShoppingCartRoute

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),

    ) {
    NavHost(
        navController = navController,
        startDestination = Screen.Register.route
    ) {
        composable(route = Screen.Register.route) {
            RegistrationRoute(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        composable(route = Screen.Login.route) {
            LoginRoute(
                onNavigateToCoffeeList = {
                    navController.navigate(Screen.CoffeeShopList.route)
                },
                navController = navController
            )
        }

        composable(route = Screen.CoffeeShopList.route) {
            CoffeeShopListRoute(
                onNavigateToCoffeeShop = { coffeeShopId ->
                    navController.navigate(Screen.CoffeeShop.createRoute(coffeeShopId))
                },
                onNavigateToMap = {
                    navController.navigate(Screen.Map.route)
                },
                navController = navController
            )
        }

        composable(
            route = Screen.CoffeeShop.route,
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            CoffeeShopMenuRoute(
                onNavigateToShoppingCart = {
                    navController.navigate(Screen.ShoppingCart.route)
                },
                navController = navController
            )
        }

        composable(route = Screen.ShoppingCart.route) {
            ShoppingCartRoute(navController)
        }

        composable(route = Screen.Map.route) {
            CoffeeShopMapRoute(
                navController = navController,
                onNavigateToCoffeeShop = { coffeeShopId ->
                    navController.navigate(Screen.CoffeeShop.createRoute(coffeeShopId))
                })
        }
    }
}
