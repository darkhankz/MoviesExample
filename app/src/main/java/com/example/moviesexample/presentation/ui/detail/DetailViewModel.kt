package com.example.moviesexample.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesexample.domain.models.MoviesDetailsData
import com.example.moviesexample.domain.models.trailers.TrailersResult
import com.example.moviesexample.domain.usecase.FetchTrailersUseCase
import com.example.moviesexample.domain.usecase.GetMoviesDetailsUseCase
import com.example.moviesexample.domain.usecase.room.DeleteMovieUseCase
import com.example.moviesexample.domain.usecase.room.InsertMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMoviesDetailsUseCase: GetMoviesDetailsUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val insertMovieUseCase: InsertMovieUseCase,
    private val fetchTrailersUseCase: FetchTrailersUseCase
) : ViewModel() {
    private val _moviesDetails = MutableLiveData<MoviesDetailsData>()
    val moviesDetails: LiveData<MoviesDetailsData> = _moviesDetails

    private val _movieTrailersLiveData = MutableLiveData<List<TrailersResult>>()
    val movieTrailersLiveData: LiveData<List<TrailersResult>> = _movieTrailersLiveData


    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getMoviesDetails(movieId: Int) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = getMoviesDetailsUseCase.invoke(movieId = movieId)
                _moviesDetails.value = response.body()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun insert(moviesData: MoviesDetailsData) {
        viewModelScope.launch(Dispatchers.IO) {
            insertMovieUseCase.invoke(moviesData = moviesData)
        }
    }


    fun delete(moviesData: MoviesDetailsData) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteMovieUseCase.invoke(moviesData = moviesData)
        }
    }

    fun fetchTrailers(movieId: Int) {
        viewModelScope.launch {
            val response = fetchTrailersUseCase.invoke(movieId = movieId)
            Log.d("APIResponse", response.toString())
            _movieTrailersLiveData.postValue(response.body()?.results)

        }
    }

}