package com.example.moviesexample.data.models

import com.example.moviesexample.data.models.moviesdetails.MoviesDetailsData

data class MoviesData(
    val page: Int,
    val results: List<MoviesDetailsData>,
    val total_pages: Int,
    val total_results: Int
)
