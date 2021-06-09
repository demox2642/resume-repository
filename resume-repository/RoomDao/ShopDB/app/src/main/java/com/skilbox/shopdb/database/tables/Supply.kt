package com.skilbox.shopdb.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.Instant

@Entity(
    tableName = SupplyContract.TABLE_NAME
)
data class Supply(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SupplyContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = SupplyContract.Colums.DATE)
    val date: Instant,

    @ColumnInfo(name = SupplyContract.Colums.STATUS)
    val status: String
)

data class SupplyDetailAndPrice(
    val id: Long?,
    val date: Instant,
    val supplyers_Id: Long,
    val supplyers_name: String,
    val count_product: Int,
    val price_summ: BigDecimal

)

data class TotalSupply(
    val id: Long?,
    val date: Instant,
    val count_product: Int,
    val price_summ: BigDecimal
)
