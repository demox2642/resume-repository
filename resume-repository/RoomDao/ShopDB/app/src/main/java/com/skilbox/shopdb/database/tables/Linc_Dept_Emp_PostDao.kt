package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Linc_Dept_Emp_PostDao {

    @Query("SELECT * FROM ${Linc_Dept_Emp_PosContract.TABLE_NAME}")
    suspend fun getAllLinc_Dept_Emp_Pos(): List<Linc_Dept_Emp_Pos>

    @Insert
    suspend fun insertLinc_Dept_Emp_Pos(lincDEPId: Linc_Dept_Emp_Pos)

    @Delete
    suspend fun deleteLinc_Dept_Emp_Pos(lincDEPId: Linc_Dept_Emp_Pos)

    @Query("DELETE FROM ${Linc_Dept_Emp_PosContract.TABLE_NAME} WHERE ${Linc_Dept_Emp_PosContract.Colums.ID}=:lincDEPId")
    suspend fun deleteLinc_Dept_Emp_PosById(lincDEPId: Long)

    @Query("SELECT * FROM ${Linc_Dept_Emp_PosContract.TABLE_NAME} WHERE ${Linc_Dept_Emp_PosContract.Colums.ID}=:lincDEPId")
    suspend fun getLinc_Dept_Emp_PosById(lincDEPId: Long): Linc_Dept_Emp_Pos?

    @Query("DELETE FROM ${Linc_Dept_Emp_PosContract.TABLE_NAME} WHERE ${Linc_Dept_Emp_PosContract.Colums.EMPLOYER_ID}=:empId")
    suspend fun deleteLincByEmpId(empId: Long)

    @Query("DELETE FROM ${Linc_Dept_Emp_PosContract.TABLE_NAME}")
    suspend fun clianLincList()
}
