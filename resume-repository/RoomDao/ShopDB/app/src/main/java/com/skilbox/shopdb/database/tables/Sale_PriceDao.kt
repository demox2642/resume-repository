package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Sale_PriceDao {
    @Query("SELECT * FROM ${Sale_PriceContract.TABLE_NAME}")
    suspend fun getAllSale_Price(): List<SaleWhithProductsPrice>

    @Insert
    suspend fun insertSale_Price(salePriceId: List<Sale_Price>)

    @Delete
    suspend fun deleteSale_Price(salePriceId: Sale_Price)

    @Query("DELETE FROM ${Sale_PriceContract.TABLE_NAME} WHERE ${Sale_PriceContract.Colums.ID}=:salePriceId")
    suspend fun deleteSale_PriceById(salePriceId: Long)

    @Query("SELECT * FROM ${Sale_PriceContract.TABLE_NAME} WHERE ${Sale_PriceContract.Colums.ID}=:salePriceId")
    suspend fun getSale_PriceById(salePriceId: Long): Sale_Price?

    @Query("DELETE FROM ${Sale_PriceContract.TABLE_NAME}")
    suspend fun clianSale_PriceList()
}
