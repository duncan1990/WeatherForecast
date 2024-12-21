package com.ahmety.studyapplication.model

interface WeatherRepository {
    suspend fun fetchWeather(cityName: String): WeatherResponse
}