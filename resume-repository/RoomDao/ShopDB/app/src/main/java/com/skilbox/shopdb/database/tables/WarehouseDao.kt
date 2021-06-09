package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.time.Instant

@Dao
interface WarehouseDao {

    @Query("SELECT * FROM ${WarehouseContract.TABLE_NAME}")
    suspend fun getAllWarehouse(): List<Warehouse>

    @Insert
    suspend fun insertWarehouse(warehouseId: Warehouse)

    @Delete
    suspend fun deleteWarehouse(warehouseId: Warehouse)

    @Query("DELETE FROM ${WarehouseContract.TABLE_NAME} WHERE ${WarehouseContract.Colums.ID}=:warehouseId")
    suspend fun deleteWarehouseById(warehouseId: Long)

    @Query("SELECT * FROM ${WarehouseContract.TABLE_NAME} WHERE ${WarehouseContract.Colums.ID}=:warehouseId")
    suspend fun getWarehouseById(warehouseId: Long): Warehouse?

    @Query(
        "UPDATE ${WarehouseContract.TABLE_NAME} " +
            "SET  ${WarehouseContract.Colums.COUNT} = :count_product , ${WarehouseContract.Colums.CHANGE_DATA}= :date " +
            "WHERE ${WarehouseContract.Colums.PRODUCT_ID}=:productId"
    )
    suspend fun upDateWarehouseByProduct(productId: Long, count_product: Double, date: Instant)

    @Query(
        "SELECT W.id AS ID, P.id AS product_id, P.name AS product_name, W.count, W.change_date\n" +
            "FROM warehouse W\n" +
            "LEFT JOIN PRODUCTS P ON W.product_id=P.id"
    )
    suspend fun getAllProductsInWarehouse(): List<CountsProductsInWarehouse>
}
