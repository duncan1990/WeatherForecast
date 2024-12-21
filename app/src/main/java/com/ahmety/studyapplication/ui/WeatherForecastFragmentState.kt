package com.ahmety.studyapplication.ui

import com.ahmety.studyapplication.model.WeatherResponse

sealed class WeatherForecastFragmentState {
    data object Loading : WeatherForecastFragmentState()
    data class Success(val data: WeatherResponse) : WeatherForecastFragmentState()
    data class Error(val message: String) : WeatherForecastFragmentState()
    data object Empty : WeatherForecastFragmentState()
}
