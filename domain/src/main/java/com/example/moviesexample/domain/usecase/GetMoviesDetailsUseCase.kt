package com.example.moviesexample.domain.usecase

import com.example.moviesexample.domain.repository.MoviesApiRepository

class GetMoviesDetailsUseCase(private val moviesApiRepository: MoviesApiRepository) {
    suspend fun invoke(movieId: Int) = moviesApiRepository.getMoviesDetails(movieId = movieId)
}