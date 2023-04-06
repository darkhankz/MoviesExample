package com.example.moviesexample.domain.models.trailers

data class TrailersResponse(
    val id: Int,
    val results: List<TrailersResult>
)