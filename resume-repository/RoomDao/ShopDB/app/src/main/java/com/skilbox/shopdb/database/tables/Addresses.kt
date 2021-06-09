package com.skilbox.shopdb.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = AddressesContract.TABLE_NAME)

data class Addresses(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = AddressesContract.Colums.ID)
    val id: Long? = null,

    @ColumnInfo(name = AddressesContract.Colums.CITY)
    val city: String,

    @ColumnInfo(name = AddressesContract.Colums.STREET)
    val street: String,

    @ColumnInfo(name = AddressesContract.Colums.BUILDING)
    val building: String
)
