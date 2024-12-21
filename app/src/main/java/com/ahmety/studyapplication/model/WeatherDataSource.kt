package com.ahmety.studyapplication.model

import com.ahmety.studyapplication.data.OperationCallback

interface WeatherDataSource {

    fun getWeather(callback: OperationCallback<WeatherResponse>)
    fun cancel()
}