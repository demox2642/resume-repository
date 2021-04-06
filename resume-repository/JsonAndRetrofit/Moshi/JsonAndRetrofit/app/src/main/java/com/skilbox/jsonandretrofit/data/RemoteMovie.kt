package com.skilbox.networking.data

import com.skilbox.jsonandretrofit.data.MovieReiting
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.net.URL

@JsonClass(generateAdapter = true)
data class RemoteMovie(
    @Json(name = "imdbID")
    val id: String,
    @Json(name = "Title")
    val title: String,
    @Json(name = "Year")
    val year: String,
    @Json(name = "Genre")
    val ganre: String,
    @Json(name = "Poster")
    val poster: URL,
    @Json(name = "Rated")
    val reiting: MovieReiting = MovieReiting.GENERAL,
    @Json(name = "Ratings")
    var scores: Map<String, String>

)
