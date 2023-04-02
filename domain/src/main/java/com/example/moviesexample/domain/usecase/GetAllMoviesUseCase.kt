package com.example.moviesexample.domain.usecase

import com.example.moviesexample.domain.repository.MoviesApiRepository

class GetAllMoviesUseCase(private val moviesApiRepository: MoviesApiRepository) {
    fun invoke() = moviesApiRepository.getAllMovies()
}