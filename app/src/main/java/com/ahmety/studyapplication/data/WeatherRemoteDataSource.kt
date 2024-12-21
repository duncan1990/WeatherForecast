package com.ahmety.studyapplication.data

import com.ahmety.studyapplication.model.WeatherDataSource
import com.ahmety.studyapplication.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRemoteDataSource(apiClient: ApiClient) : WeatherDataSource {

    private var call: Call<WeatherResponse>? = null
    private val service = apiClient.build()

    override fun getWeather(callback: OperationCallback<WeatherResponse>) {

        call = service.getNews()
        call?.enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it)
                    } else {
                        callback.onError(it.toString())
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.cancel()
    }
}