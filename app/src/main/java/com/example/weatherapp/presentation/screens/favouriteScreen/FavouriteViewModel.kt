package com.example.weatherapp.presentation.screens.favouriteScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.City
import com.example.weatherapp.model.favouriteModel.Favourite
import com.example.weatherapp.repository.WeatherDbrepository
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val repository: WeatherDbrepository
):ViewModel() {
    private val _favList = MutableStateFlow<List<Favourite>>(emptyList())
    val favList = _favList.asStateFlow()



    init {

        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavourites().distinctUntilChanged()
                .collect{listOfFav->
                    if(listOfFav.isNullOrEmpty()){
                        Log.d("TAG","EMPTY FAVS")
                    }
                    else{
                        _favList.value = listOfFav
                        Log.d("FAVS",":${favList.value}")
                    }
                }

        }
    }



    fun insertFavourite(favourite: Favourite) = viewModelScope.launch { repository.insertFavourite(favourite = favourite) }
    fun updateFavourite(favourite: Favourite)  = viewModelScope.launch { repository.updateFavourite(favourite =favourite) }
    fun deleteFavourite(favourite: Favourite) = viewModelScope.launch { repository.deleteFavourite(favourite = favourite) }
    fun getFavById(city: String) = viewModelScope.launch{repository.getFavById(city =city)}
}