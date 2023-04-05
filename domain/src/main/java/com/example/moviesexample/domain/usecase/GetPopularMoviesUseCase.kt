package com.example.moviesexample.domain.usecase

import com.example.moviesexample.domain.repository.MoviesApiRepository


class GetPopularMoviesUseCase(private val moviesApiRepository: MoviesApiRepository) {
    fun invoke() = moviesApiRepository.getPopularMovies()
}