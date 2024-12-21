package com.ahmety.studyapplication.di

import com.ahmety.studyapplication.data.ApiClient
import com.ahmety.studyapplication.model.WeatherRepository
import com.ahmety.studyapplication.model.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiClient(): ApiClient {
        return ApiClient
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(apiClient: ApiClient): WeatherRepository {
        return WeatherRepositoryImpl(apiClient.build())
    }
}
