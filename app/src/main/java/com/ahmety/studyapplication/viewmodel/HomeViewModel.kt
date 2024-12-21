package com.ahmety.studyapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmety.studyapplication.data.OperationCallback
import com.ahmety.studyapplication.model.WeatherRepository
import com.ahmety.studyapplication.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    private val _weather = MutableLiveData<WeatherResponse>().apply { postValue(WeatherResponse()) }
    val weather: LiveData<WeatherResponse> = _weather

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun loadWeather() {
        _isViewLoading.postValue(true)
        repository.fetchWeather(object : OperationCallback<WeatherResponse> {
            override fun onSuccess(data: WeatherResponse?) {
                _isViewLoading.postValue(false)
                if (data == null) {
                    _isEmptyList.postValue(true)
                } else {
                    _weather.postValue(data)
                }
            }

            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                error?.let { e -> _onMessageError.postValue(e) }
            }
        })
    }
}