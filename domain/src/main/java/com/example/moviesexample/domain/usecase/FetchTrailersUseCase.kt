package com.example.moviesexample.domain.usecase

import com.example.moviesexample.domain.repository.MoviesApiRepository

class FetchTrailersUseCase(private val moviesApiRepository: MoviesApiRepository) {
    suspend fun invoke(movieId: Int) = moviesApiRepository.fetchTrailers(movieId = movieId)
}