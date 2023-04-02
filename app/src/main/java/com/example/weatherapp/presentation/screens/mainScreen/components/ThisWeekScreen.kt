package com.example.weatherapp.presentation.screens.mainScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.utils.formatDate
import com.example.utils.formatDecimals
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherItems
import com.example.weatherapp.presentation.components.WeatherStateImage


@Composable
fun ThisWeekScreen(navController: NavController, payload: DataOrException<Weather, Boolean, Exception>){

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Color.White
    ) {

        LazyColumn(modifier = Modifier.padding(2.dp), contentPadding = PaddingValues(2.dp)){
            items(items = payload.data!!.list){items: WeatherItems ->
                WeatherDetailRow(weather = items)
            }

        }


    }

}

@Composable
fun WeatherDetailRow(weather : WeatherItems) {
   Surface(
       modifier = Modifier
           .padding(3.dp)
           .fillMaxWidth(),
       color = Color.White

   ) {
       Row(
           modifier = Modifier.fillMaxWidth(),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.SpaceBetween
       ) {
           ThisWeekComponent(weather = weather)
       }

    }

}

@Composable
fun ThisWeekComponent(weather : WeatherItems){

    val imageUrl = "http://openweathermap.org/img/wn/${weather.weather[0].icon}.png"

    Surface(
        Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(CornerSize(12.dp)),
        color = Color(0xFFECE391)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                formatDate(weather.dt)
                    .split(",")[0],
                modifier = Modifier.padding(5.dp),
                color = Color.Black
            )
            Image(painter = rememberImagePainter(imageUrl),
                contentDescription = "icon image",
                modifier = Modifier.size(80.dp)
            )

            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color.Yellow
            ) {
                Text(
                    text = weather.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption,
                    color = Color.Black
                )


            }
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Blue.copy(alpha = 0.7f),
                    fontWeight = FontWeight.SemiBold
                )){
                    append(formatDecimals(weather.temp.max)+"⁰")
                }
                withStyle(style = SpanStyle(color = Color.LightGray
                )){
                    append(formatDecimals(weather.temp.min)+"⁰")
                }

            })

        }


    }


}