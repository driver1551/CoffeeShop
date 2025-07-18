package com.example.sevenwindstest.navigation

sealed class Screen(val route: String) {
    object Register : Screen("register")
    object Login : Screen("login")
    object CoffeeList: Screen("coffee_list")
}