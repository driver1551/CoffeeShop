package com.example.sevenwindstest.domain.usecase

import com.example.sevenwindstest.data.User
import com.example.sevenwindstest.data.repository.Api
import com.example.sevenwindstest.domain.model.CoffeeShop

class GetCoffeeShopsUseCase(private val api: Api) {
    suspend operator fun invoke(): Result<List<CoffeeShop>> {
        return try {
            val response = api.getCoffeeShops(
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