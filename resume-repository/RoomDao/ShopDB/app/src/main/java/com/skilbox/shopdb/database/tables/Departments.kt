package com.skilbox.shopdb.database.tables

import androidx.room.*

@Entity(
    tableName = DepartmentsContract.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Shops::class,
            parentColumns = [ShopsContract.Colums.ID],
            childColumns = [DepartmentsContract.Colums.SHOP_ID]
        )
    ]
)
data class Departments(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DepartmentsContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = DepartmentsContract.Colums.PARENT_ID)
    val parentId: Long?,

    @ColumnInfo(name = DepartmentsContract.Colums.SHOP_ID)
    val shop_id: Long,

    @ColumnInfo(name = DepartmentsContract.Colums.FULL_NAME)
    val full_name: String,

    @ColumnInfo(name = DepartmentsContract.Colums.SHORT_NAME)
    val short_name: String
)

data class ShopsWithDepartmentsAndParent(
    val departID: Long,
    val parentName: String?,
    val shopName: String,
    val full_name: String,
    val short_name: String

)

data class ShopsWithDepartments(
    @Embedded
    val departments: Departments,
    @Relation(
        parentColumn = DepartmentsContract.Colums.SHOP_ID,
        entityColumn = ShopsContract.Colums.ID
    )
    val shop: Shops
)
