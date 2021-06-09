package com.skilbox.shopdb.database.tables

import androidx.room.*

@Entity(
    tableName = ShopsContract.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Addresses::class,
            parentColumns = [AddressesContract.Colums.ID],
            childColumns = [ShopsContract.Colums.ADDRESS_ID]
        )
    ]
)
data class Shops(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ShopsContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = ShopsContract.Colums.ADDRESS_ID)
    val address_id: Long,

    @ColumnInfo(name = ShopsContract.Colums.NAME)
    val name: String
)

data class ShopsAndAdresses(

    @Embedded val shops: Shops,
    @Relation(

        parentColumn = ShopsContract.Colums.ADDRESS_ID,
        entityColumn = AddressesContract.Colums.ID
    )

    val addresses: Addresses
)
