package com.skilbox.shopdb.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(
    tableName = SaleContract.TABLE_NAME
)
data class Sale(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SaleContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = SaleContract.Colums.DATE)
    val date: Instant,

    @ColumnInfo(name = SaleContract.Colums.STATUS)
    val status: String
)

data class TotalSale(
    val id: Long?,
    val date: Instant,
    val count_product: Int,
    val quantity_price: Double
)

data class NewSale(
    val id: Long?,
    val product_name: String,
    val price: Double,
    val count_product: Int,
    val price_id: Long,
    val product_id: Long
)
