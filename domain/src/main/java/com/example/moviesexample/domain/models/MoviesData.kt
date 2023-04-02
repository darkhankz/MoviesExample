package com.example.moviesexample.domain.models

data class MoviesData(
    val page: Int,
    val results: List<MoviesDetailsData>,
    val total_pages: Int,
    val total_results: Int
)
