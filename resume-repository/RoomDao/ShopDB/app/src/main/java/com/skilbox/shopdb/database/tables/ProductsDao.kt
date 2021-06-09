package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductsDao {

    @Query("SELECT * FROM ${ProductsContract.TABLE_NAME} ")
    suspend fun getAllProductsAndUnits(): List<ProductsAndUnit>

    @Query("SELECT * FROM ${ProductsContract.TABLE_NAME} ")
    suspend fun getAllProducts(): List<Products>

    @Insert
    suspend fun insertProducts(productsId: List<Products>)

    @Delete
    suspend fun deleteProducts(productsId: Products)

    @Query("DELETE FROM ${ProductsContract.TABLE_NAME} WHERE ${ProductsContract.Colums.ID}=:productsId")
    suspend fun deleteProductsById(productsId: Long)

    @Query("SELECT * FROM ${ProductsContract.TABLE_NAME} WHERE ${ProductsContract.Colums.ID}=:productsId")
    suspend fun getProductsById(productsId: Long): Products?

    @Query("DELETE FROM ${ProductsContract.TABLE_NAME}")
    suspend fun cleanProductList()
}
