package com.skillbox.multithreading.networking

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException

object Network {

    const val MOVIE_API_KEY = "1c8be216"

    private val client = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        )
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://www.omdbapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMovieById(movieId: String): Movie? {
        return try {
            api().getMovieById(movieId, MOVIE_API_KEY).execute().body()
        } catch (e: IOException) {
            // Проблемы с интернет соединением
            Log.e("Network ERROR", "Проблемы с интернет соединением")
            null
        }
    }

    private fun api(): MovieApi {
        return retrofit.create()
    }
}
