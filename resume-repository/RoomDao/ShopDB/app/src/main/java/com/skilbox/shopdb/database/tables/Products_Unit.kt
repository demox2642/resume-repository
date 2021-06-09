package com.skilbox.shopdb.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Products_UnitContract.TABLE_NAME)
data class Products_Unit(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Products_UnitContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = Products_UnitContract.Colums.NAME)
    val name: String,

    @ColumnInfo(name = Products_UnitContract.Colums.SHORT_NAME)
    val short_name: String
)
