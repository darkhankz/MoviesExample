package com.example.moviesexample.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.moviesexample.domain.models.MoviesDetailsData
import retrofit2.Response

interface MoviesApiRepository {
    fun getPopularMovies(): LiveData<PagingData<MoviesDetailsData>>
    suspend fun getMoviesDetails(movieId: Int): Response<MoviesDetailsData>
}