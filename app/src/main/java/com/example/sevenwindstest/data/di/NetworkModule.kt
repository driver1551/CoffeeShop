package com.example.sevenwindstest.data.di

import com.example.sevenwindstest.data.repository.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://212.41.30.90:35005/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideApi(retrofit: Retrofit): Api =
        retrofit.create(Api::class.java)

}