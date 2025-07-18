package com.example.sevenwindstest.navigation

sealed class Screen(val route: String) {
    object Register : Screen("register")
    object Login : Screen("login")
    object CoffeeShopList: Screen("coffee_shop_list")
    object CoffeeShop: Screen("coffee_shop")
}