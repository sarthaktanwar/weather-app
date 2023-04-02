package com.example.weatherapp.repository

import com.example.weatherapp.data.WeatherDao
import com.example.weatherapp.model.favouriteModel.Favourite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbrepository @Inject constructor(
    private val weatherDao: WeatherDao
) {

    fun getFavourites(): Flow<List<Favourite>> = weatherDao.getFavourites()
    suspend fun insertFavourite(favourite: Favourite) = weatherDao.insertFavourite(favourite)
    suspend fun updateFavourite(favourite: Favourite) = weatherDao.updateFavourite(favourite = favourite)
    suspend fun deleteAllFavourites() = weatherDao.deleteFavourites()
    suspend fun deleteFavourite(favourite: Favourite) = weatherDao.deleteFavourite(favourite =favourite)
    suspend fun getFavById(city: String):Favourite = weatherDao.getFavById(city = city)
}