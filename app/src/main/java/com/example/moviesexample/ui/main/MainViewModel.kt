package com.example.moviesexample.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.moviesexample.domain.models.MoviesDetailsData
import com.example.moviesexample.domain.usecase.GetAllMoviesUseCase

class MainViewModel(private val getAllMoviesUseCase: GetAllMoviesUseCase) : ViewModel() {
    private val _moviesPopular = MutableLiveData<PagingData<MoviesDetailsData>>()
    val moviesPopular: LiveData<PagingData<MoviesDetailsData>> = _moviesPopular

    fun getAllMovies() {
        val responseLiveData = getAllMoviesUseCase.invoke()
        responseLiveData.observeForever { response ->
            Log.d("APIResponse", response.toString())
            _moviesPopular.postValue(response)
        }
    }
}