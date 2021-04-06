package com.skilbox.jsonandretrofit.data.network

import com.skilbox.jsonandretrofit.data.MovieReiting
import com.skilbox.jsonandretrofit.data.Score
import com.skilbox.networking.data.RemoteMovie
import com.squareup.moshi.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.net.URL
import java.net.URLEncoder.*

class MovieCustomAdapter() {

    @FromJson
    fun fromJson(customMovies: CustomeMovie): RemoteMovie {
        return RemoteMovie(
            customMovies.id,
            customMovies.title,
            customMovies.year,
            customMovies.ganre,
            stringToURL(customMovies.poster),
            customMovies.reiting,
            listToMap(customMovies.scores)
        )
    }

    @ToJson
    fun toJson(writer: JsonWriter, movie: RemoteMovie) {
        writer.beginObject()
        writer.name("Title")
        movie.title
        writer.name("Year")
        movie.year
        writer.name("Genre")
        movie.ganre
        writer.name("Poster")
        movie.poster.toString()
        writer.name("Rated")
        movie.reiting
        writer.name("Ratings")
        writer.beginArray()
        writer.name("Source")
        movie.scores.keys
        writer.name("Value")
        movie.scores.values
        writer.endArray()
        writer.endObject()
    }

    @JsonClass(generateAdapter = true)
    data class CustomeMovie(
        @Json(name = "imdbID")
        val id: String,
        @Json(name = "Title")
        val title: String,
        @Json(name = "Year")
        val year: String,
        @Json(name = "Genre")
        val ganre: String,
        @Json(name = "Poster")
        val poster: String,
        @Json(name = "Rated")
        val reiting: MovieReiting = MovieReiting.GENERAL,
        @Json(name = "Ratings")
        val scores: List<Score> = emptyList()
    )

    fun listToMap(list: List<Score>): Map<String, String> {
        return list.map { it.source to it.value }.toMap()
    }

    fun stringToURL(string: String): URL {
        return string.toHttpUrl().toUrl()
    }
}
