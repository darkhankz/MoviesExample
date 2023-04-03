package com.example.moviesexample.presentation.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.moviesexample.databinding.FragmentDetailBinding
import com.example.moviesexample.domain.models.MoviesDetailsData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMoviesDetails()
        initObservers()
        observeLoadingAndErrors()

    }


    private fun getMoviesDetails() {
        val movieId = arguments?.getInt("movie_id")
        if (movieId != null) {
            viewModel.getMoviesDetails(movieId = movieId)
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
        binding.moviesDetailsTitle.text = movieDetails.title
        binding.moviesDetailsBodyOverview.text = movieDetails.overview
        binding.moviesDetailsDate.text = movieDetails.release_date
        binding.moviesDetailsScore.text = movieDetails.vote_average.toString()
        context?.let {
            Glide.with(it.applicationContext)
                .load("https://image.tmdb.org/t/p/w300" + movieDetails.backdrop_path)
                .into(binding.moviesDetailsImageBanner)
        }
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

}
