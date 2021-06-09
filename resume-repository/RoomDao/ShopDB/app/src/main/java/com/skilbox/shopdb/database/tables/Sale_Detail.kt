package com.skilbox.shopdb.database.tables

import androidx.room.*

@Entity(
    tableName = Sale_DetailContract.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Sale::class,
            parentColumns = [SaleContract.Colums.ID],
            childColumns = [Sale_DetailContract.Colums.SALE_ID]
        ),
        ForeignKey(
            entity = Sale_Price::class,
            parentColumns = [ProductsContract.Colums.ID],
            childColumns = [Sale_DetailContract.Colums.SALE_PRICE_ID]
        )
    ]

)
data class Sale_Detail(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Sale_DetailContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = Sale_DetailContract.Colums.SALE_ID)
    val sale_id: Long,

    @ColumnInfo(name = Sale_DetailContract.Colums.SALE_PRICE_ID)
    val sale_price_id: Long,

    @ColumnInfo(name = Sale_DetailContract.Colums.COUNT)
    val count: Double
)

data class SaleWhithSale_Detail(
    @Embedded
    val sale: Sale,
    @Relation(
        parentColumn = SaleContract.Colums.ID,
        entityColumn = Sale_DetailContract.Colums.SALE_ID
    )
    val saleDetail: List<Sale_Detail>
)

data class Sale_PriceWhithSale_Detail(
    @Embedded
    val salePrice: Sale_Price,
    @Relation(
        parentColumn = Sale_PriceContract.Colums.ID,
        entityColumn = Sale_DetailContract.Colums.SALE_PRICE_ID
    )
    val saleDetail: List<Sale_Detail>
)
