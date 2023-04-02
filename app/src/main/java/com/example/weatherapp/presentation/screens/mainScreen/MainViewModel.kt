package com.example.weatherapp.presentation.screens.mainScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
 class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
 ) :ViewModel(){


    private val _state = mutableStateOf(false)
    val state: State<Boolean> = _state

     suspend fun getWeatherData(city : String) : DataOrException<Weather,Boolean,Exception>{
         return repository.getWeather(cityQuery = city)
     }

}