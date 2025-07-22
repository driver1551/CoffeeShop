package com.example.sevenwindstest.domain.model

import com.example.sevenwindstest.data.dto.CoffeeShop

data class CoffeeShopWithDistance(
    val coffeeShop: CoffeeShop,
    val distanceMeters: Float
)
