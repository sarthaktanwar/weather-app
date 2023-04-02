package com.example.weatherapp.repository

import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api:WeatherApi
)   {
    suspend fun getWeather (cityQuery:String):DataOrException<Weather,Boolean,Exception> {


        val response = try {
            api.getWeather(query = cityQuery)

        }catch (e:Exception){

            return DataOrException(e = e)

        }
        return DataOrException(data = response)

    }

}