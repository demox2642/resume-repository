package com.skilbox.shopdb.database.tables

import androidx.room.*

@Entity(
    tableName = ProductsContract.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Products_Unit::class,
            parentColumns = [Products_UnitContract.Colums.ID],
            childColumns = [ProductsContract.Colums.PRODUCTS_UNIT_ID]
        )
    ]
)
data class Products(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ProductsContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = ProductsContract.Colums.PRODUCTS_UNIT_ID)
    val products_unit_id: Long,

    @ColumnInfo(name = ProductsContract.Colums.LABEL)
    val label: String,

    @ColumnInfo(name = ProductsContract.Colums.NAME)
    val name: String,

    @ColumnInfo(name = ProductsContract.Colums.TITLE)
    val title: String
)

data class Products_UnitWhithProducts(
    @Embedded
    val productsUnit: Products_Unit,
    @Relation(
        parentColumn = Products_UnitContract.Colums.ID,
        entityColumn = ProductsContract.Colums.PRODUCTS_UNIT_ID
    )
    val products: List<Products>
)

data class ProductsAndUnit(

    @Embedded
    val products: Products,
    @Relation(
        parentColumn = ProductsContract.Colums.PRODUCTS_UNIT_ID,
        entityColumn = Products_UnitContract.Colums.ID
    )
    val productsUnit: Products_Unit
)
