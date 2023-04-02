package com.example.weatherapp.presentation.screens.mainScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.utils.formatDate
import com.example.utils.formatDateTime
import com.example.utils.formatDecimals
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.presentation.components.WeatherStateImage



@Composable
fun MainMiddleScreen(
    payload: DataOrException<Weather, Boolean, Exception>,
    date : Int,
    temperature:String = "54",
    precipitation: String = "90%",
    pressure: String = "1024 psi",
    windSpeed: String = "8 mph",
    sunrise: String = "11:34 AM",
    sunset : String = "08:21 PM",
    modifier: Modifier = Modifier

) {
     val imageUrl = "http://openweathermap.org/img/wn/${payload.data!!.list[0].weather[0].icon}.png"


    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .height(350.dp)
    ) {

        Row (

           modifier = Modifier
               .fillMaxWidth()
               .padding(3.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = formatDate(payload.data!!.list[0].dt),
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        // for the box
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(

                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(Color.Yellow)
                    .padding(24.dp)
            ){
                Column(
                    modifier
                        .padding(6.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                    WeatherStateImage(imageUrl = imageUrl)

                    Text(
                        text =" ${payload.data!!.list[0].temp.day.toString()}⁰",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,

                    )


                }

            }

        }
        // for presure and other stuff
        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Row {
                

                Image(painter = painterResource(
                    id =com.example.weatherapp.R.drawable.rain), contentDescription ="DK",
                    Modifier.size(20.dp)

                )

                Text(
                    text = "${payload.data!!.list[0].humidity.toString()}%",
                    color = Color.Black
                )
                
            }
            Row {
                Image(painter = painterResource(
                    id =com.example.weatherapp.R.drawable.pressure), contentDescription ="DK",
                    Modifier.size(20.dp)

                )
                Text(
                    text = "${pressure}PSI",
                    color = Color.Black
                )

            }
            Row {
                Image(painter = painterResource(
                    id =com.example.weatherapp.R.drawable.wind), contentDescription ="DK",
                    Modifier.size(20.dp)

                )
                Text(
                    text = "${windSpeed}mph",
                    color = Color.Black
                )

            }

        }
        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(12.dp))

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Row {

                Image(painter = painterResource(
                    id =com.example.weatherapp.R.drawable.sunrise), contentDescription ="DK",
                    Modifier.size(20.dp)

                )

                Text(text = formatDateTime(sunrise.toInt()) ,
                    color = Color.Black
                )

            }

            Row {

                Image(painter = painterResource(
                    id =com.example.weatherapp.R.drawable.sunset), contentDescription ="DK",
                    Modifier.size(20.dp)

                )
                Text(text = formatDateTime(sunset.toInt()),
                    color = Color.Black)

            }
        }






    }

}