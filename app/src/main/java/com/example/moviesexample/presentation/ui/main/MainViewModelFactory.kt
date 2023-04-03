package com.example.moviesexample.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesexample.domain.usecase.GetPopularMoviesUseCase

//class MainViewModelFactory(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) :
//    ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            MainViewModel(getPopularMoviesUseCase) as T
//        } else {
//            throw IllegalArgumentException("ViewModel Not Found")
//        }
//    }
//}