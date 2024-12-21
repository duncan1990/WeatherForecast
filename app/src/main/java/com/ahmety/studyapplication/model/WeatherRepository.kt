package com.ahmety.studyapplication.model

import com.ahmety.studyapplication.data.OperationCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherDataSource: WeatherDataSource
) {

    fun fetchWeather(callback: OperationCallback<WeatherResponse>) {
        weatherDataSource.getWeather(object : OperationCallback<WeatherResponse> {
            override fun onSuccess(data: WeatherResponse?) {
                data?.let { weather ->
                    callback.onSuccess(weather)
                } ?: run {
                    callback.onError("No data received from API")
                }
            }

            override fun onError(error: String?) {
                CoroutineScope(Dispatchers.IO).launch {
                    callback.onError("No data available from API")
                }
            }
        })
    }
}