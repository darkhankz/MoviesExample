package com.example.moviesexample.data.repository.tmdb

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.moviesexample.data.api.ApiInterface
import com.example.moviesexample.domain.models.MoviesDetailsData
import com.example.moviesexample.domain.repository.MoviesApiRepository
import retrofit2.Response

class MoviesApiRepositoryImpl : MoviesApiRepository {
    private val apiInterface = ApiInterface.create()
    override fun getAllMovies(): LiveData<PagingData<MoviesDetailsData>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                MoviePagingSource(ApiInterface.create())
            }, initialKey = 1
        ).liveData
    }

    override suspend fun getMoviesDetails(movieId: Int): Response<MoviesDetailsData> {
        return apiInterface.getMoviesDetails(movieId, API_KEY)
    }

    companion object {
        fun create(): MoviesApiRepository {
            return MoviesApiRepositoryImpl()
        }
    }
}

const val NETWORK_PAGE_SIZE = 25
