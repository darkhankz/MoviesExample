package com.example.moviesexample.presentation.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesexample.R
import com.example.moviesexample.domain.models.trailers.TrailersResult

class TrailersAdapter(private var data: List<TrailersResult>) :
    RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder>() {

    class TrailerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val trailerName: TextView = itemView.findViewById(R.id.trailer_name)
        private val playButton: ImageButton = itemView.findViewById(R.id.play_button)

        fun bind(trailer: TrailersResult) {
            trailerName.text = trailer.name
            playButton.setOnClickListener {
                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + trailer.key)
                )
                itemView.context.startActivity(webIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.trailer_item, parent, false)
        return TrailerViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

}