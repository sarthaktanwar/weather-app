package com.example.weatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.model.favouriteModel.Favourite


@Database(entities = [Favourite::class], version = 1, exportSchema = false)
abstract class WeatherDataBase :RoomDatabase() {
    abstract fun WeatherDao():WeatherDao

}