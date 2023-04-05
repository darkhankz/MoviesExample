package com.example.moviesexample.domain.usecase.room

import com.example.moviesexample.domain.models.MoviesDetailsData
import com.example.moviesexample.domain.repository.MoviesRoomRepository

class InsertMovieUseCase(private val moviesRoomRepository: MoviesRoomRepository) {
    suspend fun invoke(moviesData: MoviesDetailsData) =
        moviesRoomRepository.insertMovie(moviesData = moviesData)
}