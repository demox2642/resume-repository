package com.skilbox.shopdb.database.tables

import androidx.room.*
import java.time.Instant

@Entity(
    tableName = Linc_Dept_Emp_PosContract.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Departments::class,
            parentColumns = [DepartmentsContract.Colums.ID],
            childColumns = [Linc_Dept_Emp_PosContract.Colums.DEPT_ID]
        ),
        ForeignKey(
            entity = Positions::class,
            parentColumns = [PositionsContract.Colums.ID],
            childColumns = [Linc_Dept_Emp_PosContract.Colums.POSITION_ID]
        ),
        ForeignKey(
            entity = Employers::class,
            parentColumns = [EmployersContract.Colums.ID],
            childColumns = [Linc_Dept_Emp_PosContract.Colums.EMPLOYER_ID]
        ),
    ]
)
data class Linc_Dept_Emp_Pos(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Linc_Dept_Emp_PosContract.Colums.ID)
    val id: Long?,

    @ColumnInfo(name = Linc_Dept_Emp_PosContract.Colums.DEPT_ID)
    val dept_id: Long,

    @ColumnInfo(name = Linc_Dept_Emp_PosContract.Colums.POSITION_ID)
    val position_id: Long,

    @ColumnInfo(name = Linc_Dept_Emp_PosContract.Colums.EMPLOYER_ID)
    val employer_id: Long,

    @ColumnInfo(name = Linc_Dept_Emp_PosContract.Colums.DATA_START)
    val data_start: Instant,

    @ColumnInfo(name = Linc_Dept_Emp_PosContract.Colums.DATA_END)
    val data_end: Instant? = null

)

data class DepsWithLinc_Dept_Emp_Pos(
    @Embedded
    val departments: Departments,
    @Relation(
        parentColumn = DepartmentsContract.Colums.ID,
        entityColumn = Linc_Dept_Emp_PosContract.Colums.DEPT_ID
    )
    val lincDeptEmpPos: List<Linc_Dept_Emp_Pos>
)

data class PositionWithLinc_Dept_Emp_Pos(
    @Embedded
    val positions: Positions,
    @Relation(
        parentColumn = PositionsContract.Colums.ID,
        entityColumn = Linc_Dept_Emp_PosContract.Colums.POSITION_ID
    )
    val lincDeptEmpPos: List<Linc_Dept_Emp_Pos>
)

data class EmpWithLinc_Dept_Emp_Pos(
    @Embedded
    val employers: Employers,
    @Relation(
        parentColumn = EmployersContract.Colums.ID,
        entityColumn = Linc_Dept_Emp_PosContract.Colums.EMPLOYER_ID
    )
    val lincDeptEmpPos: List<Linc_Dept_Emp_Pos>
)
