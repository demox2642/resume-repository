package com.skillbox.multithreading.threading

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.multithreading.R
import com.skillbox.multithreading.threading.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_threading.*
import java.util.*

class ThreadingFragment : Fragment(R.layout.fragment_threading) {

    private var movieAdapter: MovieAdapter? = null
    private val movieListViewModel: MovieViewModel by viewModels()

    private val idList = listOf(
        "tt0133093", // «Матрица»
        "tt0137523", // Бойцовский клуб
        "tt0109830", // Форрест Гамп
        "tt0068646", // Крёстный отец
        "tt0167261", // Властелин колец: Две крепости
        "tt0111161", // Побег из Шоушенка
        "tt0468569" // Темный рыцарь
    )

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initList()
        observViewModelState()
        movieListViewModel.generateMovieList(idList)
        swipeRefreshLayout.setOnRefreshListener {
            Thread {
                movieListViewModel.generateMovieList(idList)
            }.start()
            initList()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initList() {
        movieAdapter = MovieAdapter()

        with(movie_list) {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observViewModelState() {
        movieListViewModel.movieLiveDataForFragment
            .observe(viewLifecycleOwner) { movies -> movieAdapter!!.submitList(movies) }
    }
}
