package com.ahmety.studyapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmety.studyapplication.model.WeatherRepository
import com.ahmety.studyapplication.ui.WeatherForecastFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastFragmentViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<WeatherForecastFragmentState>()
    val uiState: LiveData<WeatherForecastFragmentState> get() = _uiState

    var weatherPicUrl = ""

    fun loadWeather(cityName: String) {
        viewModelScope.launch {
            _uiState.postValue(WeatherForecastFragmentState.Loading)
            try {
                val response = repository.fetchWeather(cityName)
                if (response.data?.weather.isNullOrEmpty()) {
                    _uiState.postValue(WeatherForecastFragmentState.Empty)
                } else {
                    weatherPicUrl = response.data?.currentCondition?.get(0)?.weatherIconUrl?.get(0)?.value.toString()
                    _uiState.postValue(WeatherForecastFragmentState.Success(response))
                }
            } catch (e: Exception) {
                _uiState.postValue(WeatherForecastFragmentState.Error(e.localizedMessage ?: "An unknown error occurred"))
            }
        }
    }
}
