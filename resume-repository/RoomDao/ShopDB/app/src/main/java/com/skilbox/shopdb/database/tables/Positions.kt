package com.skilbox.shopdb.database.tables

import androidx.room.*

@Entity(
    tableName = PositionsContract.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Departments::class,
            parentColumns = [DepartmentsContract.Colums.ID],
            childColumns = [PositionsContract.Colums.DEPT_ID]
        )
    ]
)
data class Positions(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PositionsContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = PositionsContract.Colums.DEPT_ID)
    val dept_id: Long,

    @ColumnInfo(name = PositionsContract.Colums.NAME)
    val name: String
)

data class DepsWithPositions(
    @Embedded
    val departments: Departments,
    @Relation(
        parentColumn = DepartmentsContract.Colums.ID,
        entityColumn = PositionsContract.Colums.DEPT_ID
    )
    val positions: List<Positions>
)

data class PositionWhithPlace(
    val id: Long?,
    val name: String?,
    val plase: String?
)
