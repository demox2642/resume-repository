package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SuppliersDao {

    @Query("SELECT * FROM ${SuppliersContract.TABLE_NAME}")
    suspend fun getAllSuppliers(): List<Suppliers>

    @Insert
    suspend fun insertSuppliers(suppliersId: List<Suppliers>)

    @Delete
    suspend fun deleteSuppliers(suppliersId: Suppliers)

    @Query("DELETE FROM ${SuppliersContract.TABLE_NAME} WHERE ${SuppliersContract.Colums.ID}=:suppliersId")
    suspend fun deleteSuppliersById(suppliersId: Long)

    @Query("SELECT * FROM ${SuppliersContract.TABLE_NAME} WHERE ${SuppliersContract.Colums.ID}=:suppliersId")
    suspend fun getSuppliersById(suppliersId: Long): Suppliers?

    @Query("SELECT * FROM ${SuppliersContract.TABLE_NAME}")
    suspend fun getSuppliersAndAddress(): List<SuppliersAndAdresses>

    @Query("DELETE FROM ${SuppliersContract.TABLE_NAME}")
    suspend fun cleanSuppliesList()
}