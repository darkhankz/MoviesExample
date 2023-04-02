package com.example.moviesexample.data.repository.tmdb

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesexample.data.api.ApiInterface
import com.example.moviesexample.domain.models.MoviesDetailsData


class MoviePagingSource(private val apiService: ApiInterface) :
    PagingSource<Int, MoviesDetailsData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDetailsData> {

        return try {
            val position = params.key ?: 1
            val response = apiService.getPopularMovies(API_KEY, "en-US", position)
            LoadResult.Page(
                data = response.body()?.results ?: emptyList(),
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MoviesDetailsData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}
const val API_KEY = "6e76ecffda0a59dc4f19a343c6e7648a"
