package com.example.moviesexample.data.repository.tmdb

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.moviesexample.data.api.ApiInterface
import com.example.moviesexample.data.utils.Constants.Companion.API_KEY
import com.example.moviesexample.data.utils.Constants.Companion.NETWORK_PAGE_SIZE
import com.example.moviesexample.domain.models.MoviesDetailsData
import com.example.moviesexample.domain.repository.MoviesApiRepository
import retrofit2.Response
import javax.inject.Inject

class MoviesApiRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface): MoviesApiRepository {

    override fun getPopularMovies(): LiveData<PagingData<MoviesDetailsData>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiInterface)
            }, initialKey = 1
        ).liveData
    }

    override suspend fun getMoviesDetails(movieId: Int): Response<MoviesDetailsData> {
        return apiInterface.getMoviesDetails(movieId, API_KEY)
    }

}

