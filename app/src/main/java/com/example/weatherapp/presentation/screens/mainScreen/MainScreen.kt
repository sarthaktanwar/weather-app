package com.example.weatherapp.presentation.screens.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.presentation.screens.mainScreen.components.MainMiddleScreen
import com.example.weatherapp.presentation.screens.mainScreen.components.MainTopAppBar
import com.example.weatherapp.presentation.screens.mainScreen.components.ShowSettingDropDownMenu
import com.example.weatherapp.presentation.screens.mainScreen.components.ThisWeekScreen


@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
) {

    var unit by remember {
        mutableStateOf("imperial")
    }



    val payload = produceState<DataOrException<Weather,Boolean,Exception>>(initialValue = DataOrException(null,true,Exception(""))){
        value = mainViewModel.getWeatherData(city = city!!)
    }.value

    if(payload.loading == true){
        CircularProgressIndicator()
    }

    else if(payload.data!= null){
        MainScreenDetail(navController = navController,payload )
    }

}

@Composable
fun MainScreenDetail(
    navController: NavController,
    payload: DataOrException<Weather, Boolean, Exception>
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    val weatherItem = payload.data!!
    val imageUrl = "http://openweathermap.org/img/wn/ ${payload.data!!.list[0].weather[0].icon}.png"

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(color = Color.White)
    ) {

        if(showDialog.value){

            ShowSettingDropDownMenu(showDialog = showDialog,navController = navController)

        }



        Column {

            MainTopAppBar(

                payload,
                navController = navController,
                showDialog,
            )
            MainMiddleScreen(
               payload ,
                payload.data!!.list[0].dt,
                payload.data!!.list[0].temp.toString(),
                payload.data!!.list[0].rain.toString(),
                payload.data!!.list[0].pressure.toString(),
                payload.data!!.list[0].speed.toString(),
                payload.data!!.list[0].sunrise.toString(),
                payload.data!!.list[0].sunset.toString()
            )
            ThisWeekScreen(
                navController =navController,
                payload = payload)
        }

    }


}
/*
 date : String = "Mon,Nov 29",
    temperature:String = "54",
    precipitation: String = "90%",
    pressure: String = "1024 psi",
    windSpeed: String = "8 mph",
    sunrise: String = "11:34 AM",
    sunset : String = "08:21 PM",
    modifier: Modifier = Modifier
 */

