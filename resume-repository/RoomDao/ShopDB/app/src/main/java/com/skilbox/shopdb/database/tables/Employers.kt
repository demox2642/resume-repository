package com.skilbox.shopdb.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = EmployersContract.TABLE_NAME)
data class Employers(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = EmployersContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = EmployersContract.Colums.SURNAME_NAME)
    val surname: String,

    @ColumnInfo(name = EmployersContract.Colums.NAME)
    val name: String,

    @ColumnInfo(name = EmployersContract.Colums.MIDDLE_NAME)
    val middle_name: String,

    @ColumnInfo(name = EmployersContract.Colums.BIRTHDAY)
    val birthday: Instant
)

data class EmploersPositionPlace(

    val surname: String,
    val name: String,
    val middle_name: String,
    val birthday: Instant,
    val id: Long?,
    val position_name: String?,
    val plase: String?

)
