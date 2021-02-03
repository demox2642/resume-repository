package com.skilbox.a18_data_time

import org.threeten.bp.Instant

data class UserLocation(
    val id: Long,
    val latitude: Double,
    val longitude: Double,
    val altitude: Double,
    val speed: Float,
    val accuracy: Float,
    var currenTime: Instant,
    val imageLinc: String
)
