package com.skilbox.networking.data.network

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

object Network {

    val networkFlipperPlugin = NetworkFlipperPlugin()

    val client: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor(CustomSetingInterceptor())
            .addNetworkInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .addInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
            .build()

    const val MOVIE_API_KEY = "1c8be216"

    fun getSearchMovieCall(text: String, year: String?, type: String?): Call {
        val url = HttpUrl.Builder()
            // http://www.omdbapi.com/?apikey=[yourkey]&s
            .scheme("http")
            .host("www.omdbapi.com")
            .addQueryParameter("apikey", MOVIE_API_KEY)
            .addQueryParameter("t", text)
            .addQueryParameter("y", year)
            .addQueryParameter("type", type)
            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()

        return client.newCall(request)
    }
}
