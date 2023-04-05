package com.example.moviesexample.presentation.ui.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesexample.domain.models.MoviesDetailsData
import com.example.moviesexample.domain.usecase.room.GetAllFavoriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllFavoriteMoviesUseCase: GetAllFavoriteMoviesUseCase
) : ViewModel() {

    private val _moviesFavorite = MutableLiveData<List<MoviesDetailsData>>()
    val moviesFavorite: LiveData<List<MoviesDetailsData>> = _moviesFavorite

    fun getFavoriteMovies() {
        val responseLiveData = getAllFavoriteMoviesUseCase.invoke()
        responseLiveData.observeForever { response ->
            Log.d("APIResponse", response.toString())
            _moviesFavorite.postValue(response.asReversed())
        }
    }

}