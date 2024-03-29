package com.example.moviesexample.presentation.ui.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesexample.R
import com.example.moviesexample.data.repository.saveshared.SaveShared
import com.example.moviesexample.databinding.FragmentDetailBinding
import com.example.moviesexample.domain.models.MoviesDetailsData
import com.example.moviesexample.presentation.ui.adapter.TrailersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var favoriteClick: ImageView
    private var isFavorite = false

    private lateinit var trailersRecyclerView: RecyclerView
    private lateinit var trailersAdapter: TrailersAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        favoriteClick = binding.imgDetailFavorite
        trailersRecyclerView = binding.trailersRecyclerView
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMoviesDetails()
        initObservers()
        observeLoadingAndErrors()
        initObserversTrailers()


    }

    private fun initObserversTrailers() {
        viewModel.apply {
            movieTrailersLiveData.observe(viewLifecycleOwner) {
                if (it != null) {
                    trailersAdapter = TrailersAdapter(it)
                    trailersRecyclerView.adapter = trailersAdapter
                }
            }
        }
    }

    private fun updateFavoriteStatus(valueBoolean: Boolean, movieDetails: MoviesDetailsData) {
        favoriteClick.setOnClickListener {
            isFavorite = if (isFavorite == valueBoolean) {
                favoriteClick.setImageResource(R.drawable.ic_favorite_24)
                SaveShared.setFavorite(context, movieDetails.id.toString(), true)
                viewModel.insert(moviesData = movieDetails)
                true
            } else {
                favoriteClick.setImageResource(R.drawable.ic_favorite_border_24)
                SaveShared.setFavorite(context, movieDetails.id.toString(), false)
                viewModel.delete(moviesData = movieDetails)
                false
            }
        }
    }


    private fun getMoviesDetails() {
        val movieId = arguments?.getInt("movie_id")
        if (movieId != null) {
            viewModel.getMoviesDetails(movieId = movieId)
            viewModel.fetchTrailers(movieId = movieId)
        }
    }

    private fun initObservers() {
        viewModel.apply {
            moviesDetails.observe(viewLifecycleOwner) {
                setMovieInformation(it)
            }
        }
    }

    private fun setMovieInformation(movieDetails: MoviesDetailsData) {
        val valueBoolean = updateFavoriteButtonImage(movieDetails)
        binding.moviesDetailsTitle.text = movieDetails.title
        binding.moviesDetailsBodyOverview.text = movieDetails.overview
        binding.moviesDetailsDate.text = movieDetails.release_date
        binding.moviesDetailsScore.text = movieDetails.vote_average.toString()
        context?.let {
            Glide.with(it.applicationContext)
                .load("https://image.tmdb.org/t/p/w300" + movieDetails.backdrop_path)
                .into(binding.moviesDetailsImageBanner)
        }
        updateFavoriteStatus(movieDetails = movieDetails, valueBoolean = valueBoolean)
    }

    private fun observeLoadingAndErrors() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressDialogDetailFragment.isVisible = isLoading

            viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
                errorMessage?.let {
                    Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateFavoriteButtonImage(movieDetails: MoviesDetailsData): Boolean {
        val valueBoolean = SaveShared.getFavorite(context, movieDetails.id.toString())
        if (isFavorite != valueBoolean) {
            favoriteClick.setImageResource(R.drawable.ic_favorite_24)

        } else {
            favoriteClick.setImageResource(R.drawable.ic_favorite_border_24)
        }
        return valueBoolean
    }

}

