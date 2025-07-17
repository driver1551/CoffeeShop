package com.example.sevenwindstest.domain.usecase

import com.example.sevenwindstest.data.dto.Location
import com.example.sevenwindstest.data.repository.Api

class GetLocationsUseCase(private val api: Api) {
    suspend operator fun invoke(): Result<List<Location>> {
        return try {
            val response = api.getLocations()
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