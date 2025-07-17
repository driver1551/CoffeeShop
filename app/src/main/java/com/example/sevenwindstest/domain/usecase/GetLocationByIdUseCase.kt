package com.example.sevenwindstest.domain.usecase

import com.example.sevenwindstest.data.dto.Location
import com.example.sevenwindstest.data.repository.Api

class GetLocationByIdUseCase(private val api: Api) {
    suspend operator fun invoke(id: Long): Result<Location> {
        return try {
            val response = api.getLocationById(id)
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