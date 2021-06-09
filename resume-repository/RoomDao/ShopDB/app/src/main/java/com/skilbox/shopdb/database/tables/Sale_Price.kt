package com.skilbox.shopdb.database.tables

import androidx.room.*
import java.math.BigDecimal
import java.time.Instant

@Entity(
    tableName = Sale_PriceContract.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Products::class,
            parentColumns = [ProductsContract.Colums.ID],
            childColumns = [Sale_PriceContract.Colums.PRODUCT_ID]
        )
    ]
)
data class Sale_Price(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Sale_PriceContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = Sale_PriceContract.Colums.PRODUCT_ID)
    val product_id: Long,

    @ColumnInfo(name = Sale_PriceContract.Colums.DATE)
    val date: Instant,

    @ColumnInfo(name = Sale_PriceContract.Colums.PRICE)
    val price: BigDecimal
)

data class ListSaleWhithProductsPrice(
    @Embedded
    val products: Products,
    @Relation(
        parentColumn = ProductsContract.Colums.ID,
        entityColumn = Sale_PriceContract.Colums.PRODUCT_ID
    )
    val salePrice: List<Sale_Price>
)

data class SaleWhithProductsPrice(
    @Embedded
    val salePrice: Sale_Price,
    @Relation(
        parentColumn = Sale_PriceContract.Colums.PRODUCT_ID,
        entityColumn = ProductsContract.Colums.ID
    )
    val products: Products
)
