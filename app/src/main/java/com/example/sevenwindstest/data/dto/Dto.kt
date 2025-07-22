package com.example.sevenwindstest.data.dto

data class LoginRequest(
    val login: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val tokenLifeTime: Long
)

data class RegistrationRequest(
    val login: String,
    val password: String
)

data class RegistrationResponse(
    val token: String,
    val tokenLifeTime: String
)

data class CoffeeShop(
    val id: Int,
    val name: String,
    val point: Point
)

data class Point(
    val latitude: Double,
    val longitude: Double
)

data class CoffeeShopMenuItem(
    val id: Long,
    val name: String,
    val imageURL: String,
    val price: Long
)

data class CartItem(
    val item: CoffeeShopMenuItem,
    val quantity: Int
)
