package com.skilbox.networking

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skilbox.jsonandretrofit.R
import com.skilbox.networking.data.network.adapter.MovieListAdapter
import kotlinx.android.synthetic.main.movi_fragment.*
import kotlinx.android.synthetic.main.movi_fragment.movie_type
import kotlinx.android.synthetic.main.movi_fragment.movie_year
import kotlinx.android.synthetic.main.movie_vie_for_list.*

class MoviFragment : Fragment(R.layout.movi_fragment) {

    private var movieAdapter: MovieListAdapter? = null
    private val viewModel: MoviViewModel by viewModels()
    private var like: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()

        error_text.visibility = View.INVISIBLE
        search_button_repeat.visibility = View.INVISIBLE

        progressBar.visibility = View.INVISIBLE
        search_button.setOnClickListener { search() }
        search_button_repeat.setOnClickListener {
            search_button.visibility = View.VISIBLE
            error_text.visibility = View.GONE
            search_button_repeat.visibility = View.GONE
            movie_list.visibility = View.VISIBLE
            search()
        }
    }

    private fun initList() {
        movieAdapter = MovieListAdapter(like) { position -> myScores(position) }
        with(movie_list) {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun myScores(position: Int) {
        Log.e("myScores", "$position")
        viewModel.createScore(position)
        movieAdapter?.notifyItemChanged(position)
    }

    private fun bindViewModel() {

        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
        viewModel.movieList.observe(viewLifecycleOwner) { movieAdapter?.items = it }
        viewModel.serverError.observe(viewLifecycleOwner, ::postErrorMassage)
        viewModel.likeIsAdd.observe(viewLifecycleOwner, ::updateLikeImage)
    }

    private fun updateLoadingState(isLoading: Boolean) {

        movie_list.isVisible = isLoading.not()
        progressBar.isVisible = isLoading
        search_button.isEnabled = isLoading.not()
        movie_name.isEnabled = isLoading.not()
        movie_year.isEnabled = isLoading.not()
        movie_type.isEnabled = isLoading.not()
    }

    private fun postErrorMassage(errorServerMassage: String) {
        Log.e("postErrorMassage", "$errorServerMassage")
        if (errorServerMassage.isNotEmpty()) {
            error_text.text = errorServerMassage
            error_text.visibility = View.VISIBLE
            search_button_repeat.visibility = View.VISIBLE
            search_button.visibility = View.INVISIBLE
        }
        Log.e("ServerFragment", errorServerMassage)
    }

    private fun search() {

        val queryText = movie_name.text.toString()
        val queryYear = movie_year.text.toString()
        val queryType = when (movie_type.selectedItemId.toInt()) {
            1 -> "series"
            2 -> "movie"
            3 -> "episode"
            else -> null
        }

        Log.e("QueryType", "$queryType")
        viewModel.search(queryText, queryYear, queryType)
    }

    fun updateLikeImage(b: Boolean) {
        like = b

    }
}
