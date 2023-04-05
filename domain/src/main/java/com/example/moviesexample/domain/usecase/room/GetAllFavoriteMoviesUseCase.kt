package com.example.moviesexample.domain.usecase.room

import com.example.moviesexample.domain.repository.MoviesRoomRepository

class GetAllFavoriteMoviesUseCase(private val moviesRoomRepository: MoviesRoomRepository) {
    fun invoke() = moviesRoomRepository.getFavoriteMovies()
}