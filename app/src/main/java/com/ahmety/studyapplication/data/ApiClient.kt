package com.ahmety.studyapplication.data

import com.ahmety.studyapplication.BuildConfig
import com.ahmety.studyapplication.model.WeatherResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    private const val API_BASE_URL = "https://api.worldweatheronline.com"

    private var servicesApiInterface: ServicesApiInterface? = null

    fun build(): ServicesApiInterface {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        val retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java
        )

        return servicesApiInterface as ServicesApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServicesApiInterface {
        @GET("/premium/v1/weather.ashx?key=${BuildConfig.API_KEY}&format=json&num_of_days=5")
        suspend fun getWeather(@Query("q") cityName: String): WeatherResponse
    }
}