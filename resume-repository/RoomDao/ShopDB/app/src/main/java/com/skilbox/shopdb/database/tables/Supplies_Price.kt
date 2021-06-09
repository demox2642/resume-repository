package com.skilbox.shopdb.database.tables

import androidx.room.*
import java.math.BigDecimal
import java.time.Instant

@Entity(
    tableName = Supplies_PriceContract.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Suppliers::class,
        parentColumns = [SuppliersContract.Colums.ID],
        childColumns = [Supplies_PriceContract.Colums.SUPPLIERS_ID]
    ),
        ForeignKey(
            entity = Products::class,
            parentColumns = [ProductsContract.Colums.ID],
            childColumns = [Supplies_PriceContract.Colums.PRODUCT_ID]
        )
    ]

)
data class Supplies_Price(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Supplies_PriceContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = Supplies_PriceContract.Colums.SUPPLIERS_ID)
    val suppliers_id: Long,

    @ColumnInfo(name = Supplies_PriceContract.Colums.PRODUCT_ID)
    val product_id: Long,

    @ColumnInfo(name = Supplies_PriceContract.Colums.DATE)
    val date: Instant,

    @ColumnInfo(name = Supplies_PriceContract.Colums.PRICE)
    val price: BigDecimal
)

data class SuppliersWhithProductsPrice(
    @Embedded
    val suppliers: Suppliers,
    @Relation(
        parentColumn = SuppliersContract.Colums.ID,
        entityColumn = Supplies_PriceContract.Colums.SUPPLIERS_ID
    )
    val suppliesPrice: List<Supplies_Price>
)

data class ProductsWhithSuppliersPrice(
    @Embedded
    val products: Products,
    @Relation(
        parentColumn = ProductsContract.Colums.ID,
        entityColumn = Supplies_PriceContract.Colums.PRODUCT_ID
    )
    val suppliesPrice: List<Supplies_Price>
)

data class SuppliersPriceProduct(
    val id: Long,
    val suppliers_id: Long,
    val suplilers_name: String,
    val product_id: Long,
    val product_name: String,
    val date: Instant,
    val price: BigDecimal
)

data class ProductCountInSupply(
    val id: Long,
    val suppliers_id: Long,
    val suplilers_name: String,
    val product_id: Long,
    val product_name: String,
    val date: Instant,
    val price: BigDecimal,
    val product_quantity:Int
)

