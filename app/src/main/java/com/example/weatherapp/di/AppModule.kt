package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.WeatherDao
import com.example.weatherapp.data.WeatherDataBase
import com.example.weatherapp.network.WeatherApi
import com.example.weatherapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesWeatherDao(weatherDataBase: WeatherDataBase):WeatherDao
    = weatherDataBase.WeatherDao()

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context):WeatherDataBase =
        Room.databaseBuilder(
            context,
            WeatherDataBase::class.java,
            "weather_database").fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideOpenWeatherApi():WeatherApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }


}