package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShopsDao {

    @Query("SELECT * FROM ${ShopsContract.TABLE_NAME}")
    suspend fun getAllShops(): List<Shops>

    @Insert
    suspend fun insertShops(shops: List<Shops>)

    @Query("DELETE FROM ${ShopsContract.TABLE_NAME}")
    suspend fun clianShopList()


    @Query("SELECT * FROM  ${ShopsContract.TABLE_NAME} ")
    suspend fun getShopsWhithAddresses(): List<ShopsAndAdresses>

    @Query("DELETE FROM ${ShopsContract.TABLE_NAME} WHERE ${ShopsContract.Colums.ID}=:shopId")
    suspend fun deleteShopsById(shopId: Long)

    @Query("SELECT * FROM ${ShopsContract.TABLE_NAME} WHERE ${ShopsContract.Colums.ID}=:shopId")
    suspend fun getShopsById(shopId: Long): Shops?
}