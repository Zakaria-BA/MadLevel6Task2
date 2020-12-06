package com.example.madlevel6task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.madlevel6task2.Model.Movie
import com.example.madlevel6task2.R
import com.example.madlevel6task2.adapter.MovieAdapter
import com.example.madlevel6task2.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.movie_overview_fragment.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

const val BUNDLE_MOVIE_KEY = "bundle_movie_key"
const val REQ_MOVIE_KEY = "req_movie_key"
class MovieOverviewFragment : Fragment() {

    private val movies = arrayListOf<Movie>()
    private val moviesAdapter = MovieAdapter(movies) { onMovieClick(it)}
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.movie_overview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeMovies()

        submitButton.setOnClickListener {
            didTappedSubmit()
        }

    }

    private fun initView() {

        rvMovies.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvMovies.adapter = moviesAdapter
    }

    private fun didTappedSubmit() {

        var year = etYear.text

        if (year.isNullOrBlank()) {

        } else {
            var yearint: Int = year.toString().toInt()
            viewModel.getMovies(yearint)
        }
    }

    private fun onMovieClick(movie: Movie) {
        setFragmentResult(REQ_MOVIE_KEY, bundleOf(Pair(BUNDLE_MOVIE_KEY, movie)))
        findNavController().navigate(R.id.action_movieOverviewFragment_to_movieDetailFragment)
    }

    private fun observeMovies() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            movies.clear()
            movies.addAll(it)
            moviesAdapter.notifyDataSetChanged()
        })

        // Observe the error message.
        viewModel.errorText.observe(viewLifecycleOwner, Observer {

        })
    }
}