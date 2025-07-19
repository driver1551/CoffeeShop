package com.example.sevenwindstest.data.repository

import com.example.sevenwindstest.data.dto.CoffeeShop
import com.example.sevenwindstest.data.dto.LoginRequest
import com.example.sevenwindstest.data.dto.LoginResponse
import com.example.sevenwindstest.data.dto.RegistrationRequest
import com.example.sevenwindstest.data.dto.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun registration(@Body request: RegistrationRequest): Response<RegistrationResponse>

    @GET("locations")
    suspend fun getCoffeeShops(
        @Header("Authorization") token: String
    ): Response<List<CoffeeShop>>

    @GET("locations/{id}")
    suspend fun getCoffeeShopMenuById(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    ): Response<CoffeeShop>
}

