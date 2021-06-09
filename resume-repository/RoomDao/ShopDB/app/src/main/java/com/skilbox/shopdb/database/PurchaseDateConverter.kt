package com.skilbox.shopdb.database

import androidx.room.TypeConverter
import java.time.Instant

class PurchaseDateConverter {

    @TypeConverter
    fun convertDateToString(date: Instant?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun convertStringToDate(string: String?): Instant? {
        return if (string == null) {
            null
        } else {
            Instant.parse(string)
        }
    }
}
