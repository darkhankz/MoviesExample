package com.example.moviesexample.di

import com.example.moviesexample.data.api.ApiInterface
import com.example.moviesexample.data.db.MoviesDao
import com.example.moviesexample.data.repository.room.RoomRepositoryImpl
import com.example.moviesexample.data.repository.tmdb.MoviesApiRepositoryImpl
import com.example.moviesexample.domain.repository.MoviesApiRepository
import com.example.moviesexample.domain.repository.MoviesRoomRepository
import com.example.moviesexample.domain.usecase.FetchTrailersUseCase
import com.example.moviesexample.domain.usecase.GetMoviesDetailsUseCase
import com.example.moviesexample.domain.usecase.GetPopularMoviesUseCase
import com.example.moviesexample.domain.usecase.room.DeleteMovieUseCase
import com.example.moviesexample.domain.usecase.room.GetAllFavoriteMoviesUseCase
import com.example.moviesexample.domain.usecase.room.InsertMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideMoviesApiRepository(apiInterface: ApiInterface): MoviesApiRepository {
        return MoviesApiRepositoryImpl(apiInterface = apiInterface)
    }

    @Provides
    @Singleton
    fun providesGetPopularMoviesUseCase(moviesApiRepository: MoviesApiRepository): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(moviesApiRepository = moviesApiRepository)

    @Provides
    @Singleton
    fun providesGetMoviesDetailsUseCase(moviesApiRepository: MoviesApiRepository): GetMoviesDetailsUseCase =
        GetMoviesDetailsUseCase(moviesApiRepository = moviesApiRepository)

    @Provides
    @Singleton
    fun providesGetAllFavoriteMoviesUseCase(moviesRoomRepository: MoviesRoomRepository): GetAllFavoriteMoviesUseCase =
        GetAllFavoriteMoviesUseCase(moviesRoomRepository = moviesRoomRepository)

    @Provides
    fun provideMoviesRoomRepository(moviesDao: MoviesDao): MoviesRoomRepository {
        return RoomRepositoryImpl(moviesDao = moviesDao)
    }

    @Provides
    @Singleton
    fun providesDeleteMovieUseCase(moviesRoomRepository: MoviesRoomRepository): DeleteMovieUseCase =
        DeleteMovieUseCase(moviesRoomRepository = moviesRoomRepository)

    @Provides
    @Singleton
    fun providesInsertMovieUseCase(moviesRoomRepository: MoviesRoomRepository): InsertMovieUseCase =
        InsertMovieUseCase(moviesRoomRepository = moviesRoomRepository)

    @Provides
    @Singleton
    fun providesGetFetchTrailersMoviesUseCase(moviesApiRepository: MoviesApiRepository): FetchTrailersUseCase =
        FetchTrailersUseCase(moviesApiRepository = moviesApiRepository)
}
