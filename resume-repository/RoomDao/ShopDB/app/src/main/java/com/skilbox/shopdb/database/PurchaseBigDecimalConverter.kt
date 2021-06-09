package com.skilbox.shopdb.database

import androidx.room.TypeConverter
import java.math.BigDecimal

class PurchaseBigDecimalConverter {

    @TypeConverter
    fun convertBigDecimalToString(bigDecimal: BigDecimal): String = bigDecimal.toString()

    @TypeConverter
    fun convertStringToDate(string: String): BigDecimal = string.toBigDecimal()
}
