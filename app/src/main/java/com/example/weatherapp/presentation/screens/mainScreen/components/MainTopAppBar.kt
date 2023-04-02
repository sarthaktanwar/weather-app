package com.example.weatherapp.presentation.screens.mainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.favouriteModel.Favourite
import com.example.weatherapp.navigation.WeatherScreen
import com.example.weatherapp.presentation.screens.favouriteScreen.FavouriteViewModel
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.CopyOnWriteArrayList

//@Preview

@Composable
fun MainTopAppBar(
    payload: DataOrException<Weather, Boolean, Exception>,
    navController: NavController,
    showDialog: MutableState<Boolean>,
    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
    onActionButtonClicked :()->Unit = {},
    onButtonClicked: ()->Unit = {}


) {
    var cityTop =  payload.data!!.city.name
    var countryTop = payload.data!!.city.country

        TopAppBar(
            elevation = 12.dp,
            title = {
                Text("${payload.data!!.city.name},${payload.data!!.city.country}")
            },

            backgroundColor =  Color(0xFFE3C9E7),
            contentColor = Color.Black,

            actions = {

                IconButton(onClick = {
                    favouriteViewModel.insertFavourite(Favourite(
                        city =cityTop,
                        country = countryTop
                    ))
                }) {
                    Icon(
                        Icons.Default.Favorite, null,
                    )
                }

                IconButton(onClick = {
                    navController.navigate(WeatherScreen.SeachScreen.name)
                }) {
                    Icon(Icons.Filled.Search, null)
                }
                IconButton(onClick = {
                    showDialog.value= !showDialog.value
                }) {
                    Icon(Icons.Filled.Settings, null)
                }

            })
}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {

    var expanded by remember {
        mutableStateOf(true)
    }
    val itemsList:List<String> = listOf("about","favourite","setting")


      Column(modifier = Modifier
          .fillMaxWidth()
          .wrapContentSize(Alignment.TopEnd)
          .absolutePadding(top = 45.dp, right = 20.dp)) {
          DropdownMenu(expanded = expanded, onDismissRequest = {
              expanded = false
          },
              modifier = Modifier
                  .width(160.dp)
                  .background(Color.White)
          ) {
              itemsList.forEachIndexed{index,text->
                  DropdownMenuItem(onClick = {
                      expanded = false
                      showDialog.value = false
                  }) {
                      
                      Icon(imageVector = when(text){
                                 "about"-> Icons.Default.Info
                                 "favourite"->Icons.Default.Favorite
                                  else -> Icons.Default.Settings }
                          , contentDescription =null ,
                          tint = Color.Gray

                      )
                      Text(
                          text = text,
                          modifier = Modifier.clickable {

                                                        when(text){
                                                            "about"->navController.navigate(WeatherScreen.AboutScreen.name)
                                                            "favourite"->navController.navigate(WeatherScreen.FavouriteSceen.name)
                                                            else->navController.navigate(WeatherScreen.SettingScreen.name)
                                                        }
                          },
                          fontWeight = FontWeight.W300,
                          color = Color.Black
                      )

                  }

              }
               }

          }
      }







