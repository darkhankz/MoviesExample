package com.example.moviesexample.data.di

import android.content.Context
import androidx.room.Room
import com.example.moviesexample.data.api.ApiInterface
import com.example.moviesexample.data.db.MoviesDao
import com.example.moviesexample.data.db.MoviesDatabase
import com.example.moviesexample.data.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideMoviesApiService(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            MoviesDatabase::class.java, "movies_database")
            .build()

    @Provides
    fun provideMoviesDao(database: MoviesDatabase): MoviesDao {
        return database.getMoviesDao()
    }

}