package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Products_UnitDao {
    @Query("SELECT * FROM ${Products_UnitContract.TABLE_NAME}")
    suspend fun getAllProducts_Unit(): List<Products_Unit>

    @Insert
    suspend fun insertProducts_Unit(productsUnitId: List<Products_Unit>)

    @Delete
    suspend fun deleteProducts_Unit(productsUnitId: Products_Unit)

    @Query("DELETE FROM ${Products_UnitContract.TABLE_NAME} WHERE ${Products_UnitContract.Colums.ID}=:productsUnitId")
    suspend fun deleteProducts_UnitById(productsUnitId: Long)

    @Query("SELECT * FROM ${Products_UnitContract.TABLE_NAME} WHERE ${Products_UnitContract.Colums.ID}=:productsUnitId")
    suspend fun getProducts_UnitById(productsUnitId: Long): Products_Unit?
}
