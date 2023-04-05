package com.example.moviesexample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesexample.domain.models.MoviesDetailsData

@Database(entities = [MoviesDetailsData::class], version = 1, exportSchema = true)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}