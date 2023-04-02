package com.example.weatherapp.presentation.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.navigation.WeatherScreen
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavController){

    SplashScreenMid(navController = navController)
}

@Composable
fun SplashScreenMid(navController: NavController){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White).padding(1.dp)
    ) {
        SplashScreenDetail(navController = navController)

    }
}


@Composable
fun SplashScreenDetail(navController: NavController){

    val defaultCity: String = "delhi"

    val scale = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true, block ={
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(4.5f)
                        .getInterpolation(it)
                }
            )

        )
        delay(2000L)
        navController.popBackStack()
        navController.navigate(WeatherScreen.MainScreen.name+"/$defaultCity")


    } )
    Surface(
        modifier = Modifier
            .size(330.dp)
            .padding(15.dp)
            .scale(scale.value),
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = Color.LightGray)

    ) {
        Column(modifier = Modifier
            .padding(1.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {

            Text(

                text = "find The Sun ?"
            )

        }

    }

}