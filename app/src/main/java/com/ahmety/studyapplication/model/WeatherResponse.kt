package com.ahmety.studyapplication.model

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class WeatherResponse (
    val data: Data? = null
)

data class Data (
    val weather: List<WeatherElement>? =  null,
    @SerializedName("current_condition")
    val currentCondition: List<CurrentCondition>? = null
)

data class WeatherElement (
    val date: String? = null,
    val maxtempC: String? = null,
    val mintempC: String? = null,
    val avgtempC: String? = null,
    val totalSnowCM: String? = null,
    val sunHour: String? = null,
    val uvIndex: String? = null,
)

data class CurrentCondition (
    val weatherDesc: List<Weather>? = null,
    val weatherIconUrl: List<Weather>? = null
)

data class Weather (
    val value: String? = null
)