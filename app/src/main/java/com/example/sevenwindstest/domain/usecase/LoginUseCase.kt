package com.example.sevenwindstest.domain.usecase

import com.example.sevenwindstest.data.User
import com.example.sevenwindstest.data.dto.LoginRequest
import com.example.sevenwindstest.data.dto.LoginResponse
import com.example.sevenwindstest.data.repository.Api

class LoginUseCase(private val api: Api) {
    suspend operator fun invoke(login: String, password: String): Result<LoginResponse> {
        return try {
            val response = api.login(LoginRequest(login, password))
            if (response.isSuccessful) {
                User.token = response.body()?.token
                User.tokenLifetime = response.body()?.tokenLifeTime
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
