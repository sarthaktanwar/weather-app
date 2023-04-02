package com.example.weatherapp.presentation.screens.aboutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AboutScreen(navController: NavController){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            Modifier.background(color = Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "build by ",
                color = Color.Black,
                fontSize = 16.sp

            )
            Text(
                text = "Sarthak tanwar",
                color = Color.Black,
                fontSize = 32.sp,
                fontStyle = FontStyle.Italic
            )

        }

    }
}