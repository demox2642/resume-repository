package com.skillbox.multithreading.threading

import android.util.Log
import com.skillbox.multithreading.networking.Movie
import com.skillbox.multithreading.networking.Network.getMovieById
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MovieRepository {
    private var movieList = listOf(
        Movie(
            title = "The Shawshank Redemption",
            year = 1994,
            poster = "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg",
            genre = "Drama", director = "Frank Darabont"
        )
    )

    fun getMovieList(): List<Movie> {
        return movieList
    }

    fun addList(
        idList: List<String>,
        callback: (List<Movie>) -> Unit
    ) {
        val getNumOfCores = Runtime.getRuntime().availableProcessors()
        val allMovie = Collections.synchronizedList(mutableListOf<Movie>())

        val executor = Executors.newFixedThreadPool(getNumOfCores)
        Thread {
            for (i in 0 until idList.size) {
                executor.submit(
                    Runnable {
                        Log.e("Runnable", "${getMovieById(idList[i])}")
                        allMovie.add(getMovieById(idList[i]))
                        Log.e("Runnable", "$allMovie")
                    }
                )
            }
            movieList = allMovie
            executor.awaitTermination(7, TimeUnit.SECONDS)
        }.start()

        Log.e("repository", "$allMovie  \n $movieList")
        Log.e("executor", "Finished all threads")

        callback(movieList)
    }
}
