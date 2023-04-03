package com.example.moviesexample.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesexample.domain.models.MoviesDetailsData
import com.example.moviesexample.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    private val _moviesPopular = MutableLiveData<PagingData<MoviesDetailsData>>()
    val moviesPopular: LiveData<PagingData<MoviesDetailsData>> = _moviesPopular

    fun getPopularMovies() {
        val responseLiveData = getPopularMoviesUseCase.invoke().cachedIn(viewModelScope )
        responseLiveData.observeForever { response ->
            Log.d("APIResponse", response.toString())
            _moviesPopular.postValue(response)
        }
    }
}