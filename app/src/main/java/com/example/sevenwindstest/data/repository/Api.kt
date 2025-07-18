package com.example.sevenwindstest.data.repository

import com.example.sevenwindstest.data.dto.LoginRequest
import com.example.sevenwindstest.data.dto.LoginResponse
import com.example.sevenwindstest.data.dto.RegistrationRequest
import com.example.sevenwindstest.data.dto.RegistrationResponse
import com.example.sevenwindstest.data.dto.Location
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun registration(@Body request: RegistrationRequest): Response<RegistrationResponse>

    @GET("locations")
    suspend fun getLocations(): Response<List<Location>>

    @GET("locations/{id}")
    suspend fun getLocationById(@Path("id") id: Long): Response<Location>
}

