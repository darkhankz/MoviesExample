package com.example.moviesexample.data.repository.room

import androidx.lifecycle.LiveData
import com.example.moviesexample.data.db.MoviesDao
import com.example.moviesexample.domain.models.MoviesDetailsData
import com.example.moviesexample.domain.repository.MoviesRoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private val moviesDao: MoviesDao) :
    MoviesRoomRepository {
    override fun getFavoriteMovies(): LiveData<List<MoviesDetailsData>> {
        return moviesDao.getAllFavoriteMovies()
    }

    override suspend fun insertMovie(moviesData: MoviesDetailsData) {
        moviesDao.insert(moviesData = moviesData)
    }

    override suspend fun deleteMovie(moviesData: MoviesDetailsData) {
        moviesDao.delete(moviesData = moviesData)
    }
}