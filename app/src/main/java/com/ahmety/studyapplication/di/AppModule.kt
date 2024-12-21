package com.ahmety.studyapplication.di

import android.content.Context
import androidx.room.Room
import com.ahmety.studyapplication.data.ApiClient
import com.ahmety.studyapplication.data.WeatherRemoteDataSource
import com.ahmety.studyapplication.model.WeatherDataSource
import com.ahmety.studyapplication.model.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideWeatherRepository(newsDataSource: WeatherDataSource): WeatherRepository {
        return WeatherRepository(newsDataSource)
    }

    @Singleton
    @Provides
    fun provideWeatherRemoteDataSource(apiClient: ApiClient): WeatherDataSource {
        return WeatherRemoteDataSource(apiClient)
    }

}