package com.skilbox.shopdb.database.tables

import androidx.room.*

@Entity(
    tableName = Supply_DetailContract.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Supply::class,
        parentColumns = [SupplyContract.Colums.ID],
        childColumns = [Supply_DetailContract.Colums.SUPPLY_ID]
    ),
        ForeignKey(
            entity = Supplies_Price::class,
            parentColumns = [Supplies_PriceContract.Colums.ID],
            childColumns = [Supply_DetailContract.Colums.SUPPLY_PRICE_ID]
        )
    ]

)
data class Supply_Detail(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Supply_DetailContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = Supply_DetailContract.Colums.SUPPLY_ID)
    val supply_id: Long,

    @ColumnInfo(name = Supply_DetailContract.Colums.SUPPLY_PRICE_ID)
    val supply_price_id: Long,

    @ColumnInfo(name = Supply_DetailContract.Colums.COUNT)
    val count: Double
)

data class SupplyWhithSupply_Detail(
    @Embedded
    val sale: Supply,
    @Relation(
        parentColumn = SupplyContract.Colums.ID,
        entityColumn = Supply_DetailContract.Colums.SUPPLY_ID
    )
    val supplyDetail: List<Supply_Detail>
)

data class Supplies_PriceWhith_Detail(
    @Embedded
    val salePrice: Supplies_Price,
    @Relation(
        parentColumn = Supplies_PriceContract.Colums.ID,
        entityColumn = Supply_DetailContract.Colums.SUPPLY_PRICE_ID
    )
    val supplyDetail: List<Supply_Detail>
)

