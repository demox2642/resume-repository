package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Supply_DetailDao {

    @Query("SELECT * FROM ${Supply_DetailContract.TABLE_NAME}")
    suspend fun getAllSupply_Detail(): List<Supply_Detail>

    @Insert
    suspend fun insertSupply_Detail(supplyDetailId: List<Supply_Detail>)

    @Delete
    suspend fun deleteSupply_Detail(supplyDetailId: Supply_Detail)

    @Query("DELETE FROM ${Supply_DetailContract.TABLE_NAME} WHERE ${Supply_DetailContract.Colums.ID}=:supplyDetailId")
    suspend fun deleteSupply_DetailById(supplyDetailId: Long)

    @Query("SELECT * FROM ${Supply_DetailContract.TABLE_NAME} WHERE ${Supply_DetailContract.Colums.ID}=:supplyDetailId")
    suspend fun getSupply_DetailById(supplyDetailId: Long): Supply_Detail?

    @Query("DELETE FROM ${Supply_DetailContract.TABLE_NAME} ")
    suspend fun clianSupplyDetail()
}
