package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SaleDao {
    @Query("SELECT * FROM ${SaleContract.TABLE_NAME}")
    suspend fun getAllSale(): List<Sale>

    @Insert
    suspend fun insertSale(saleId: List<Sale>)

    @Delete
    suspend fun deleteSale(saleId: Sale)

    @Query("DELETE FROM ${SaleContract.TABLE_NAME} WHERE ${SaleContract.Colums.ID}=:saleId")
    suspend fun deleteSaleById(saleId: Long)

    @Query("SELECT * FROM ${SaleContract.TABLE_NAME} WHERE ${SaleContract.Colums.ID}=:saleId")
    suspend fun getSaleById(saleId: Long): Sale?

    @Query(
        "SELECT S.id AS id , S.date as date,-- p.name as product_name, \n" +
            "sum(sd.count) as count_product,sum(sd.count * sp.price) as quantity_price--,SP.id AS price_id,p.id AS product_id\n" +
            "            FROM SALE S\n" +
            "            LEFT JOIN SALE_DETAIL SD ON  SD.sale_id=S.id\n" +
            "            LEFT JOIN SALE_PRICE SP ON SD.sale_price_id=SP.id\n" +
            "            LEFT JOIN PRODUCTS P ON SP.product_id=P.id\n" +
            "            GROUP BY S.id,S.date  "
    )
    suspend fun getTotalSale(): List<TotalSale>

    @Query(
        "SELECT W.id AS id, P.name AS product_name, SP.price AS price, W.count AS count_product,SP.id AS price_id,p.id AS product_id\n" +
            "FROM WAREHOUSE W\n" +
            "LEFT JOIN PRODUCTS P ON W.product_id=P.id\n" +
            "LEFT JOIN SALE_PRICE SP ON W.product_id =SP.product_id"
    )
    suspend fun getPreoductForSale(): List<NewSale>

    @Query("SELECT max( ${SaleContract.Colums.ID}) FROM  ${SaleContract.TABLE_NAME}")
    suspend fun getLastSale(): Long

    @Query("DELETE FROM ${SaleContract.TABLE_NAME}")
    suspend fun clianSaleList()
}
