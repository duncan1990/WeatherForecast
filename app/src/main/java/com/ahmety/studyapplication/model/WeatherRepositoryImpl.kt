package com.ahmety.studyapplication.model

import com.ahmety.studyapplication.data.ApiClient
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiInterface: ApiClient.ServicesApiInterface
) : WeatherRepository {
    override suspend fun fetchWeather(cityName: String): WeatherResponse {
        return apiInterface.getWeather(cityName)
    }
}