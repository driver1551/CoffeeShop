package com.example.sevenwindstest.domain.model

data class CoffeeShop(
    val id: Int,
    val name: String,
    val point: Point
)

data class Point(
    val latitude: Double,
    val longitude: Double
)
