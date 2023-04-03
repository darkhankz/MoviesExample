package com.example.moviesexample.presentation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviesexample.databinding.FragmentMainBinding
import com.example.moviesexample.presentation.ui.adapter.MoviePagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private val adapter = MoviePagerAdapter()



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
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.moviesPopular.observe(viewLifecycleOwner) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                }
            }
            viewModel.getPopularMovies()
        }
    }


}