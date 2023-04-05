package com.example.moviesexample.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesexample.R
import com.example.moviesexample.domain.models.MoviesDetailsData

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    var mList = listOf<MoviesDetailsData>()

    private var onMovieClickListener: OnMovieClickListener? = null

    fun setOnMovieClickListener(listener: OnMovieClickListener) {
        onMovieClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_disgn, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w300" + itemsViewModel.poster_path)
            .into(holder.imageView)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = itemsViewModel.title

        holder.itemView.rootView.setOnClickListener {
            onMovieClickListener?.onMovieClick(itemsViewModel.id)

        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.name)
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
    }

    interface OnMovieClickListener {
        fun onMovieClick(movieId: Int)
    }

}
