package com.skillbox.multithreading.networking

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: Int,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Genre")
    val genre: String,
    @SerializedName("Director")
    val director: String

)
