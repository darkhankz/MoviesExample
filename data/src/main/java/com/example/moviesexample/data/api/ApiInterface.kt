package com.example.moviesexample.data.api


import com.example.moviesexample.domain.models.MoviesData
import com.example.moviesexample.domain.models.MoviesDetailsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MoviesData>

    @GET("movie/{movie_id}")
    suspend fun getMoviesDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ) : Response<MoviesDetailsData>

}

const val BASE_URL = "https://api.themoviedb.org/3/"
