package com.skillbox.multithreading.threading

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.multithreading.networking.Movie

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val movieListLiveData = MutableLiveData<List<Movie>>(
        repository.getMovieList()
    )

    val movieLiveDataForFragment: LiveData<List<Movie>>
        get() = movieListLiveData

    fun generateMovieList(idMovie: List<String>) {
        repository.addList(idMovie) { movies ->
            Log.e("generateMovieList", "$movies")
            movieListLiveData.postValue(movies)
        }
    }
}
