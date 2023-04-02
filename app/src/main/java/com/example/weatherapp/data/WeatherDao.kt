package com.example.weatherapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.model.favouriteModel.Favourite
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao{

    @Query("SELECT * FROM fav_tbl")
    fun getFavourites(): Flow<List<Favourite>>


    @Query("SELECT * from fav_tbl where city =:city")
    suspend fun getFavById(city : String): Favourite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: Favourite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favourite: Favourite)


    @Query("DELETE from fav_tbl")
    suspend fun deleteFavourites()

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)




}