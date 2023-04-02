package com.example.moviesexample.data.models

data class MoviesDetailsData(
    val id: Int,
    val backdrop_path: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
)
