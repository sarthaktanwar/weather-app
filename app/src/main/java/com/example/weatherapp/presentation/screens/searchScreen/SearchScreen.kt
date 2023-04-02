package com.example.weatherapp.presentation.screens.searchScreen


import android.util.Log
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weatherapp.navigation.WeatherScreen
import com.example.weatherapp.presentation.screens.mainScreen.MainViewModel


@Composable
fun SearchScreen(
    navController: NavHostController ,
    mainViewModel: MainViewModel = hiltViewModel()

) {
       
       Surface(
           modifier = Modifier
           .fillMaxSize()
           .padding(0.dp)
           .background(color = Color.White)

           ) {
           
           Column(
               Modifier.fillMaxSize().background(color = Color.White),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Top
           ) {
               TopAppBar(
                   elevation = 12.dp,
                   title = {
                       Text("Search")
                   },
                   backgroundColor =  Color(0xFFE3C9E7),
                   contentColor = Color.Black,

                   actions = {
                       IconButton(onClick = {
                           navController.popBackStack()
                       }) {
                           Icon(Icons.Filled.ArrowBack, null)
                       }

                   })

               Spacer(modifier = Modifier
                   .fillMaxWidth()
                   .height(24.dp))


               SearchBar(
                   modifier = Modifier
                       .padding(16.dp)
                       .fillMaxWidth()


               ){mcity->
                   navController.navigate(WeatherScreen.MainScreen.name+"/${mcity}")
               }

           }
           
       }





}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String)->Unit = {}
){

   val  searchQueryState= rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController= LocalSoftwareKeyboardController.current
    val validState= remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotBlank()
    }

        OutlinedSearch(
            valueState = searchQueryState,
            placeHolder ="Seattle",
            onAction = KeyboardActions {
                if(!validState)return@KeyboardActions
                onSearch(searchQueryState.value.trim())

                searchQueryState.value = ""
                keyboardController?.hide()
            }
        )




}



@Composable
fun OutlinedSearch(
    valueState : MutableState<String>,
    placeHolder : String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction : KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(value = valueState.value,
        onValueChange = {
            valueState.value =  it
        },
        textStyle = TextStyle(color = Color.Black),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor =Color.Blue,
            cursorColor = Color.Black,
            backgroundColor = Color(0xFFF4F8F8)

        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)


    )

}
