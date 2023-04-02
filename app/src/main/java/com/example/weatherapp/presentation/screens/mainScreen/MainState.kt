package com.example.weatherapp.presentation.screens.mainScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class MainState(
    var showDialog : MutableState<Boolean> = mutableStateOf(true)
)
