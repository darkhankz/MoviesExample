package com.example.moviesexample.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesexample.domain.usecase.GetAllMoviesUseCase

class MainViewModelFactory(private val getAllMoviesUseCase: GetAllMoviesUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(getAllMoviesUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}