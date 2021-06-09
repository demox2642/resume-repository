package com.skilbox.shopdb.database.tables

import androidx.room.*

@Entity(
    tableName = SuppliersContract.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Addresses::class,
        parentColumns = [AddressesContract.Colums.ID],
        childColumns = [SuppliersContract.Colums.ADDRESS_ID]
    )
    ]
)
data class Suppliers(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SuppliersContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = SuppliersContract.Colums.ADDRESS_ID)
    val address_id: Long,

    @ColumnInfo(name = SuppliersContract.Colums.NAME)
    val name: String
)


data class SuppliersAndAdresses(
    @Embedded val suppliers: Suppliers,
    @Relation(
        parentColumn = SuppliersContract.Colums.ID,
        entityColumn = AddressesContract.Colums.ID
    )
    val addresses: Addresses
)