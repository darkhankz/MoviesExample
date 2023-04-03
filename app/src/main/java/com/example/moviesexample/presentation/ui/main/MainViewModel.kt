package com.example.moviesexample.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesexample.domain.models.MoviesDetailsData
import com.example.moviesexample.domain.usecase.GetPopularMoviesUseCase
import com.example.moviesexample.presentation.ui.adapter.MoviePagerAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    private val _moviesPopular = MutableLiveData<PagingData<MoviesDetailsData>>()
    val moviesPopular: LiveData<PagingData<MoviesDetailsData>> = _moviesPopular

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    fun getPopularMovies() {
        val responseLiveData = getPopularMoviesUseCase.invoke().cachedIn(viewModelScope )
        responseLiveData.observeForever { response ->
            Log.d("APIResponse", response.toString())
            _moviesPopular.postValue(response)
        }
    }

    fun initLoadState(adapter: MoviePagerAdapter) {
        adapter.addLoadStateListener { loadState ->
            // update loading state
            _loading.value = loadState.refresh is LoadState.Loading ||
                    loadState.append is LoadState.Loading
            // update error message
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }
            errorState?.let {
                Log.d("dataCheck", "initLoadState: error=${it.error}")
                _errorMessage.value = it.error.toString()
            }
        }
    }
}