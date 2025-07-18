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
