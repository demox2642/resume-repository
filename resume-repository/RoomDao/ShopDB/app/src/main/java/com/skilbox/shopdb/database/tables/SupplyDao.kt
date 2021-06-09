package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SupplyDao {

    @Query("SELECT * FROM ${SupplyContract.TABLE_NAME}")
    suspend fun getAllSupply(): List<Supply>

    @Insert
    suspend fun insertSupply(supplyId: Supply)

    @Delete
    suspend fun deleteSupply(supplyId: Supply)

    @Query("DELETE FROM ${SupplyContract.TABLE_NAME} WHERE ${SupplyContract.Colums.ID}=:supplyId")
    suspend fun deleteSupplyById(supplyId: Long)

    @Query("SELECT * FROM ${SupplyContract.TABLE_NAME} WHERE ${SupplyContract.Colums.ID}=:supplyId")
    suspend fun getSupplyById(supplyId: Long): Supply?

    @Query(
        " SELECT S.id AS id, S.date AS date, SU.id AS supplyers_Id, SU.name AS supplyers_name,  \n" +
            "             SUM(SD.count) AS count_product, SUM(SD.count*SP.price) AS price_summ \n" +
            "             FROM SUPPLY S \n" +
            "             LEFT JOIN SUPPLY_DETAIL SD ON S.id=SD.supply_id \n" +
            "             LEFT JOIN SUPPLIERS_PRICE SP ON SD.supply_price_id=SP.id  \n" +
            "             LEFT JOIN SUPPLIERS SU ON SP.suppliers_id=SU.id \n" +
            "             GROUP BY  S.id , S.date , SU.id , SU.name "
    )
    suspend fun getSupplyDetailAndPrice(): List<SupplyDetailAndPrice>

    @Query(
        " SELECT  s.id,s.date, sum(sd.count *sp.price) as count_product , sum(sd.count) as price_summ\n" +
            "             FROM SUPPLY S \n" +
            "             LEFT JOIN SUPPLY_DETAIL SD ON S.id=SD.supply_id \n" +
            "             LEFT JOIN SUPPLIERS_PRICE SP ON SD.supply_price_id=SP.id  \n" +
            "             LEFT JOIN SUPPLIERS SU ON SP.suppliers_id=SU.id \n" +
            "             Group by  s.id,s.date"
    )
    suspend fun getTotalSupply(): List<TotalSupply>

    @Query("SELECT max(${SupplyContract.Colums.ID}) FROM ${SupplyContract.TABLE_NAME}")
    suspend fun getLastSupply(): Long

    @Query("DELETE FROM ${SupplyContract.TABLE_NAME}")
    suspend fun clianSupplyList()

    // suspend fun getDetailBySupplyId(supplyId: Long):List<ProductCountInSupply>
}
