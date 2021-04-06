package com.skillbox.github.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserForChange(
    val name: String,
    val email: String,
    @Json(name = "location")
    val location_user: String,
    val blog: String,
    val twitter_username: String,
    val company: String,
    val hireable: Boolean
)
