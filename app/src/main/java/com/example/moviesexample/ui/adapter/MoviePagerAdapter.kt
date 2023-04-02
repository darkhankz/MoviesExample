package com.example.moviesexample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesexample.databinding.CardViewDisgnBinding
import com.example.moviesexample.domain.models.MoviesDetailsData


class MoviePagerAdapter : PagingDataAdapter<MoviesDetailsData, MoviePagerAdapter.MovieViewHolder>(MovieComparator) {

    private var onMovieClickListener: OnMovieClickListener? = null

    fun setOnMovieClickListener(listener: OnMovieClickListener) {
        onMovieClickListener = listener
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)!!
        holder.view.name.text = movie.title
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w300" + movie.poster_path).into(holder.view.imageview)
        holder.view.root.setOnClickListener {
            onMovieClickListener?.onMovieClick(movie.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardViewDisgnBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    class MovieViewHolder(val view: CardViewDisgnBinding) : RecyclerView.ViewHolder(view.root)

    object MovieComparator : DiffUtil.ItemCallback<MoviesDetailsData>() {
        override fun areItemsTheSame(
            oldItem: MoviesDetailsData,
            newItem: MoviesDetailsData
        ): Boolean {
            // Id is unique.
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: MoviesDetailsData,
            newItem: MoviesDetailsData
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface OnMovieClickListener {
        fun onMovieClick(movieId: Int)
    }
}