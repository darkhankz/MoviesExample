package com.example.moviesexample.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviesexample.data.repository.tmdb.MoviesApiRepositoryImpl
import com.example.moviesexample.databinding.FragmentMainBinding
import com.example.moviesexample.domain.repository.MoviesApiRepository
import com.example.moviesexample.domain.usecase.GetAllMoviesUseCase
import com.example.moviesexample.ui.adapter.MoviePagerAdapter
import kotlinx.coroutines.launch


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private val adapter = MoviePagerAdapter()

    private val moviesApiRepository: MoviesApiRepository = MoviesApiRepositoryImpl()

    private val getAllMoviesUseCase: GetAllMoviesUseCase by lazy {
        GetAllMoviesUseCase(moviesApiRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.recyclerViewFragment.adapter = adapter
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModelFactory()
        observer()
    }
    private fun observer() {
        lifecycleScope.launch {
            viewModel.moviesPopular.observe(viewLifecycleOwner) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                }
            }
            viewModel.getAllMovies()
        }
    }

    private fun initViewModelFactory() {
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                getAllMoviesUseCase = getAllMoviesUseCase
            )
        )[MainViewModel::class.java]
    }
}