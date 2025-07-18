package com.example.sevenwindstest.data.di

import com.example.sevenwindstest.data.repository.Api
import com.example.sevenwindstest.domain.usecase.GetCoffeeShopByIdUseCase
import com.example.sevenwindstest.domain.usecase.GetCoffeeShopsUseCase
import com.example.sevenwindstest.domain.usecase.LoginUseCase
import com.example.sevenwindstest.domain.usecase.RegistrationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideLoginUseCase(api: Api): LoginUseCase = LoginUseCase(api)

    @Provides
    fun provideRegistrationUseCase(api: Api): RegistrationUseCase = RegistrationUseCase(api)

    @Provides
    fun provideGetLocationsUseCase(api: Api): GetCoffeeShopsUseCase = GetCoffeeShopsUseCase(api)

    @Provides
    fun provideGetLocationByIdUseCase(api: Api): GetCoffeeShopByIdUseCase =
        GetCoffeeShopByIdUseCase(api)
}