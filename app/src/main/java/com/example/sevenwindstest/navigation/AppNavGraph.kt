package com.example.sevenwindstest.navigation

import androidx.compose.material3.Text
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
import com.example.sevenwindstest.ui.registration.RegistrationRoute

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
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
                }
            )
        }

        composable(route = Screen.CoffeeShopList.route) {
            CoffeeShopListRoute(
                onNavigateToCoffeeShop = { coffeeShopId ->
                    navController.navigate(Screen.CoffeeShop.createRoute(coffeeShopId))
                }
            )
        }

        composable(
            route = Screen.CoffeeShop.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            CoffeeShopMenuRoute()
        }
    }
}
