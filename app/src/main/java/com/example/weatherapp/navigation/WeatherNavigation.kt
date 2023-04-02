package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.presentation.screens.aboutScreen.AboutScreen
import com.example.weatherapp.presentation.screens.favouriteScreen.FavouriteScreen
import com.example.weatherapp.presentation.screens.mainScreen.MainScreen
import com.example.weatherapp.presentation.screens.mainScreen.MainViewModel
import com.example.weatherapp.presentation.screens.searchScreen.SearchScreen
import com.example.weatherapp.presentation.screens.settingScreen.SettingScreen
import com.example.weatherapp.presentation.screens.splash.WeatherSplashScreen


@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreen.SplashScreen.name){
        composable(WeatherScreen.SplashScreen.name){
            WeatherSplashScreen(navController = navController)

        }
        val route = WeatherScreen.MainScreen.name
        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city"){
                    type = NavType.StringType
                }
            )){navBack->
            navBack.arguments!!.getString("city").let {city->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController,mainViewModel,city= city)
            }


        }
        composable(WeatherScreen.SeachScreen.name){

            val mainViewModel = hiltViewModel<MainViewModel>()
            SearchScreen(navController = navController,mainViewModel)
        }
        composable(WeatherScreen.SettingScreen.name){
            SettingScreen(navController = navController)
        }

        composable(WeatherScreen.AboutScreen.name){
            AboutScreen(navController)
        }
         composable(WeatherScreen.FavouriteSceen.name){
            FavouriteScreen(navController = navController)
        }


    }
}