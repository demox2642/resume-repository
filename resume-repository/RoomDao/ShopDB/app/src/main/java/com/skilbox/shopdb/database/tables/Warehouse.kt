package com.skilbox.shopdb.database.tables

import androidx.room.*
import java.time.Instant

@Entity(
    tableName = WarehouseContract.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Products::class,
            parentColumns = [ProductsContract.Colums.ID],
            childColumns = [WarehouseContract.Colums.PRODUCT_ID]
        )
    ]
)
data class Warehouse(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = WarehouseContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = WarehouseContract.Colums.PRODUCT_ID)
    var product_id: Long,

    @ColumnInfo(name = WarehouseContract.Colums.COUNT)
    val count: Double,

    @ColumnInfo(name = WarehouseContract.Colums.CHANGE_DATA)
    val change_date: Instant
)

data class ProductsInWarehouse(
    @Embedded
    val products: Products,
    @Relation(
        parentColumn = ProductsContract.Colums.ID,
        entityColumn = WarehouseContract.Colums.PRODUCT_ID
    )
    val warehouse: List<Warehouse>
)

data class CountsProductsInWarehouse(
    val id: Long?,
    val product_id: Long,
    val product_name: String,
    val count: Double,
    val change_date: Instant
)
