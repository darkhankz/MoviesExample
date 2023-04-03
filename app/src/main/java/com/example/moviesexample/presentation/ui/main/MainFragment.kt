package com.example.moviesexample.presentation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.moviesexample.R
import com.example.moviesexample.databinding.FragmentMainBinding
import com.example.moviesexample.presentation.ui.adapter.MoviePagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val moviesAdapter = MoviePagerAdapter()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.recyclerViewFragment.adapter = moviesAdapter
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPopularMovies()
        initObserver()
        initAdapterClickListener()
        observeLoadingAndErrors()
    }


    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.moviesPopular.observe(viewLifecycleOwner) {
                it?.let {
                    moviesAdapter.submitData(lifecycle, it)
                }
            }
        }
    }

    private fun initAdapterClickListener() {
        moviesAdapter.setOnMovieClickListener(object : MoviePagerAdapter.OnMovieClickListener {
            override fun onMovieClick(movieId: Int) {
                val bundle = bundleOf("movie_id" to movieId)
                view?.findNavController()?.navigate(
                    R.id.action_mainFragment_to_detailFragment, bundle)
            }
        })
    }

    private fun observeLoadingAndErrors() {
        viewModel.initLoadState(moviesAdapter)
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressDialog.isVisible = isLoading
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
            }
        }

    }

}