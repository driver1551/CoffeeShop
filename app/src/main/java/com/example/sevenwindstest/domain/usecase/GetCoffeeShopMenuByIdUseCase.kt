package com.example.sevenwindstest.domain.usecase

import com.example.sevenwindstest.data.User
import com.example.sevenwindstest.data.dto.CoffeeShopMenuItem
import com.example.sevenwindstest.data.repository.Api

class GetCoffeeShopMenuByIdUseCase(private val api: Api) {
    suspend operator fun invoke(id: Long): Result<List<CoffeeShopMenuItem>> {
        return try {
            val response = api.getCoffeeShopMenuById(
                id = id,
                token = "Bearer ${User.token!!}"
            )
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