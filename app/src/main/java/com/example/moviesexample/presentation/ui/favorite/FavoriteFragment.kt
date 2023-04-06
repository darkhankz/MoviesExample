package com.example.moviesexample.presentation.ui.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesexample.R
import com.example.moviesexample.databinding.FragmentFavoriteBinding
import com.example.moviesexample.presentation.ui.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()

    private lateinit var mMoviesRecycler: RecyclerView
    private val mMoviesAdapter by lazy { FavoriteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root
        mMoviesRecycler = binding.recyclerViewFavoriteFragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMoviesRecycler.layoutManager = GridLayoutManager(context, 2)
        mMoviesRecycler.adapter = mMoviesAdapter
        initObservers()
        initAdapterClickListener()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewModel.moviesFavorite.observe(viewLifecycleOwner) { list ->
            mMoviesAdapter.mList = list
            mMoviesAdapter.notifyDataSetChanged()
        }
        viewModel.getFavoriteMovies()
    }

    private fun initAdapterClickListener() {
        mMoviesAdapter.setOnMovieClickListener(object : FavoriteAdapter.OnMovieClickListener {
            override fun onMovieClick(movieId: Int) {
                val bundle = bundleOf("movie_id" to movieId)
                view?.findNavController()?.navigate(
                    R.id.action_favoriteFragment_to_detailFragment, bundle
                )
            }

        })
    }

}