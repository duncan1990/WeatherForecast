package com.ahmety.studyapplication.data

import com.ahmety.studyapplication.model.WeatherResponse

interface OperationCallback<T> {
    fun onSuccess(data: WeatherResponse?)
    fun onError(error: String?)
}