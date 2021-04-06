package com.skilbox.networking.data

import android.util.Log
import com.skilbox.jsonandretrofit.data.network.MovieCustomAdapter
import com.skilbox.networking.data.network.Network
import com.squareup.moshi.Moshi
import okio.IOException
import kotlin.Exception

class MovieRepository {
    private var movieList: MutableList<RemoteMovie> = mutableListOf()
    private var errorMassage: String = ""
    private var like: Boolean = false

    fun searchMovie(
        text: String,
        year: String?,
        type: String?,
        callback: (List<RemoteMovie>) -> Unit

    ) {
// синхронный метод запроса
        Thread {

            try {
                val responce = Network.getSearchMovieCall(text, year, type).execute()

                val responeString = responce.body?.string().orEmpty()
                Log.d("Server", "responce successful $responeString")
                Log.d("Server", "responce successful ${responce.isSuccessful}")
                movieList = parseMovieResponse(responeString).toMutableList()

                callback(movieList)
            } catch (e: IOException) {
                Log.e("Server", "execute request error:${e.message}", e)
                errorMassage = e.message.toString()
                callback(emptyList())
            }
        }.start()

        Log.d("Server", "responce successful $movieList")
    }

    private fun parseMovieResponse(responseBodyString: String): List<RemoteMovie> {
        val moshi = Moshi.Builder()
            .add(MovieCustomAdapter())
            .build()

        val adapter = moshi.adapter(RemoteMovie::class.java).nonNull()

        try {
            movieList = emptyList<RemoteMovie>().toMutableList()
            val movie = adapter.fromJson(responseBodyString)

            movieList.add(movie!!)
            return movieList
        } catch (e: java.lang.Exception) {
            Log.e("Server jsonObject", "Ответ с сервера не корректный = ${e.message}", e)
            errorMassage = e.message.toString()
            return emptyList()
        }
    }

    fun getErrorMassage(): String {
        return errorMassage
    }

    fun addMyScories(position: Int) {
        Log.e("In repository", "$position")

        if (movieList[position].scores.containsKey("My scores")) {
            movieList[position].scores = movieList[position].scores.minus("My scores")
            like = false
        } else {
            movieList[position].scores += mapOf("My scores" to "${(0..100).random()}%")
            like = true
        }

        Log.e("addMyScories", "${movieList[position]}")
    }

    fun getMyScories(): Boolean {
        return like
    }
}
