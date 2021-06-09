package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Supplies_PriceDao {

    @Query("SELECT * FROM ${Supplies_PriceContract.TABLE_NAME}")
    suspend fun getAllSupplies_Price(): List<Supplies_Price>

    @Insert
    suspend fun insertSupplies_Price(suppliesPriceId: List<Supplies_Price>)

    @Delete
    suspend fun deleteSupplies_Price(suppliesPriceId: Supplies_Price)

    @Query("DELETE FROM ${Supplies_PriceContract.TABLE_NAME} WHERE ${Supplies_PriceContract.Colums.ID}=:suppliesPriceId")
    suspend fun deleteSupplies_PriceById(suppliesPriceId: Long)

    @Query("SELECT * FROM ${Supplies_PriceContract.TABLE_NAME} WHERE ${Supplies_PriceContract.Colums.ID}=:suppliesPriceId")
    suspend fun getSupplies_PriceById(suppliesPriceId: Long): Supplies_Price?

    @Query(
        "SELECT SP.id as id , S.id as suppliers_id,S.name as suplilers_name ,P.id as product_id, P.name as product_name, SP.date as date, SP.price as price\n" +
            "FROM SUPPLIERS_PRICE SP\n" +
            "LEFT JOIN SUPPLIERS S ON S.id=SP.suppliers_id\n" +
            "LEFT JOIN PRODUCTS P ON SP.product_id=P.id"
    )
    suspend fun getListSuppliesPriceAndProduct(): List<SuppliersPriceProduct>

    @Query("DELETE FROM ${Supplies_PriceContract.TABLE_NAME}")
    suspend fun clianSupplies_PriceList()
}
