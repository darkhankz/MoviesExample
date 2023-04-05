package com.example.moviesexample.domain.repository

import androidx.lifecycle.LiveData
import com.example.moviesexample.domain.models.MoviesDetailsData

interface MoviesRoomRepository {
    fun getFavoriteMovies(): LiveData<List<MoviesDetailsData>>
    suspend fun insertMovie(moviesData: MoviesDetailsData)
    suspend fun deleteMovie(moviesData: MoviesDetailsData)
}