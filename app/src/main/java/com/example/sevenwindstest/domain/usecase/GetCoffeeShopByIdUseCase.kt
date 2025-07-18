package com.example.sevenwindstest.domain.usecase

import com.example.sevenwindstest.data.User
import com.example.sevenwindstest.data.repository.Api
import com.example.sevenwindstest.domain.model.CoffeeShop

class GetCoffeeShopByIdUseCase(private val api: Api) {
    suspend operator fun invoke(id: Long): Result<CoffeeShop> {
        return try {
            val response = api.getCoffeeShopById(
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