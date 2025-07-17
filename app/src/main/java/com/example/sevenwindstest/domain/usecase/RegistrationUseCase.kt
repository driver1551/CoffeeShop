package com.example.sevenwindstest.domain.usecase

import com.example.sevenwindstest.data.dto.RegistrationRequest
import com.example.sevenwindstest.data.dto.RegistrationResponse
import com.example.sevenwindstest.data.repository.Api

class RegistrationUseCase(private val api: Api) {
    suspend operator fun invoke(login: String, password: String): Result<RegistrationResponse> {
        return try {
            val response = api.registration(request = RegistrationRequest(login, password))
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}