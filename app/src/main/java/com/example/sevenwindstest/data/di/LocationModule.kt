package com.example.sevenwindstest.data.di

import android.content.Context
import com.example.sevenwindstest.data.repository.LocationClient
import com.example.sevenwindstest.domain.usecase.DefaultLocationClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    fun provideLocationClient(
        @ApplicationContext context: Context
    ): LocationClient {
        return DefaultLocationClient(context)
    }
}
