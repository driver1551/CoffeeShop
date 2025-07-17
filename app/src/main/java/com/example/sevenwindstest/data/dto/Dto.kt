package com.example.sevenwindstest.data.dto

data class LoginRequest(
    val login: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val tokenLifeTime: String
)

data class RegistrationRequest(
    val login: String,
    val password: String
)

data class RegistrationResponse(
    val token: String,
    val tokenLifeTime: String
)

data class Location(
    val id: Long,
    val name: String,
    val point: Point,
)

data class Point(
    val latitude: Double,
    val longitude: Double,
)
