package com.example.weatherapp.presentation.screens.favouriteScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.model.favouriteModel.Favourite
import com.example.weatherapp.navigation.WeatherScreen
import okhttp3.internal.parseCookie

@Composable
fun FavouriteScreen(
    navController: NavController,
    favouriteViewModel: FavouriteViewModel = hiltViewModel()
){
   Surface(
       modifier = Modifier
           .fillMaxSize()
           .background(color = Color.White)
   ) {
       Column(
           modifier = Modifier
               .fillMaxSize()
               .background(color = Color.White)
               .padding(6.dp),
           verticalArrangement = Arrangement.Top,
           horizontalAlignment = Alignment.CenterHorizontally

       ) {
           val list =  favouriteViewModel.favList.collectAsState().value
           
           LazyColumn{
               items(items = list){
                   CityRow(it,navController = navController,favouriteViewModel)
               }
           }


       }

    }

}

@Composable
fun CityRow(
    favourite: Favourite,
    navController: NavController,
    favouriteViewModel: FavouriteViewModel) {

    val cityname = favourite.city

  Surface(
      modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
          .padding(3.dp)
          .clickable {

                     navController.navigate(WeatherScreen.MainScreen.name +"/${cityname}")

          },
      shape = CircleShape.copy(CornerSize(3.dp)),
      color = Color(0xFFDAC9AD)
  ) {
      Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
          Text(text = favourite.city, modifier = Modifier.padding(4.dp))
          Surface(modifier = Modifier.padding(0.dp),
              shape = CircleShape,
              color = Color(0xFFB6AA96)
          ) {
              Text(text = favourite.country,
                  modifier = Modifier.padding(3.dp),
                  style = MaterialTheme.typography.caption
              )


              
          }
          Icon(
              imageVector = Icons.Default.Delete,
              contentDescription = "delete icon",
              modifier = Modifier.clickable {
                  favouriteViewModel.deleteFavourite(favourite = favourite)
              },
              tint = Color.Red.copy(alpha = 0.3f)
          )
          
      }
      

    }


}
