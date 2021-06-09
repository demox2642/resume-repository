package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Sale_DetailDao {
    @Query("SELECT * FROM ${Sale_DetailContract.TABLE_NAME}")
    suspend fun getAllSale_Detail(): List<Sale_Detail>

    @Insert
    suspend fun insertSale_Detail(saleDetailId: List<Sale_Detail>)

    @Delete
    suspend fun deleteSale_Detail(saleDetailId: Sale_Detail)

    @Query("DELETE FROM ${Sale_DetailContract.TABLE_NAME} WHERE ${Sale_DetailContract.Colums.ID}=:saleDetailId")
    suspend fun deleteSale_DetailById(saleDetailId: Long)

    @Query("SELECT * FROM ${Sale_DetailContract.TABLE_NAME} WHERE ${Sale_DetailContract.Colums.ID}=:saleDetailId")
    suspend fun getSale_DetailById(saleDetailId: Long): Sale_Detail?

    @Query("DELETE FROM ${Sale_DetailContract.TABLE_NAME} WHERE ${Sale_DetailContract.Colums.SALE_ID}=:saleId")
    suspend fun deleteSale_DetailBySaleID(saleId: Long)

    @Query("DELETE FROM ${Sale_DetailContract.TABLE_NAME}")
    suspend fun clianSaleDetail()
}
