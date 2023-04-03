package com.example.moviesexample.di

import com.example.moviesexample.data.api.ApiInterface
import com.example.moviesexample.data.repository.tmdb.MoviesApiRepositoryImpl
import com.example.moviesexample.domain.repository.MoviesApiRepository
import com.example.moviesexample.domain.usecase.GetMoviesDetailsUseCase
import com.example.moviesexample.domain.usecase.GetPopularMoviesUseCase
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
        return MoviesApiRepositoryImpl(apiInterface)
    }

    @Provides
    @Singleton
    fun providesGetPopularMoviesUseCase(moviesApiRepository: MoviesApiRepository): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(moviesApiRepository = moviesApiRepository)

    @Provides
    @Singleton
    fun providesGetMoviesDetailsUseCase(moviesApiRepository: MoviesApiRepository): GetMoviesDetailsUseCase =
        GetMoviesDetailsUseCase(moviesApiRepository = moviesApiRepository)
}
