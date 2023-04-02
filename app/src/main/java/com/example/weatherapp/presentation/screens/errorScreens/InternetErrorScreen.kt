package com.example.weatherapp.presentation.screens.errorScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun internetErrorScreen(e: Exception){

    Surface(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Blue)
    ) {

    }

    Text(text = "${e.toString()}")

}