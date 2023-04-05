package com.example.moviesexample.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviesexample.domain.models.MoviesDetailsData

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(moviesData: MoviesDetailsData)

    @Delete
    suspend fun delete(moviesData: MoviesDetailsData)

    @Query("SELECT * FROM movie_table")
    fun getAllFavoriteMovies(): LiveData<List<MoviesDetailsData>>

}
