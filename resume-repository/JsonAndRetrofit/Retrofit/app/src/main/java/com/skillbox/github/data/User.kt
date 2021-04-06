package com.skillbox.github.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(

        val id: Long,
        @Json(name = "login")
        val user_name: String,
        val avatar_url: String,
        val name: String?,
        val email: String?,
        @Json(name = "location")
        val location_user: String?,
        val blog: String?,
        val twitter_username: String?,
        val company: String?,
        val hireable: Boolean?,
        val bio: String?
)
