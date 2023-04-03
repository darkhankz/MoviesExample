package com.example.moviesexample.data.di

import com.example.moviesexample.data.api.ApiInterface
import com.example.moviesexample.data.api.BASE_URL
import com.example.moviesexample.data.repository.tmdb.API_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun baseUrl() = BASE_URL
    fun apiKey() = API_KEY

    @Provides
    fun provideMoviesApiService(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiInterface::class.java)

    }

}