package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DepartmentsDao {

    @Query("SELECT * FROM ${DepartmentsContract.TABLE_NAME}")
    suspend fun getShopsWithDepartments(): List<ShopsWithDepartments>

    @Query(
        "SELECT d.id as departID,p.full_name as parentName,s.name as shopName,d.full_name,d.short_name \n" +
            "            FROM departments d\n" +
            "            INNER JOIN shops s ON s.id=d.shop_id\n" +
            "            LEFT JOIN departments p ON d.parent_id=p.id"
    )

    suspend fun getShopsWithDepartmentsAndParent(): List<ShopsWithDepartmentsAndParent>

    @Query("SELECT * FROM ${DepartmentsContract.TABLE_NAME}")
    suspend fun getShopsAndDeparts(): List<ShopsWithDepartments>

    @Query("SELECT * FROM ${DepartmentsContract.TABLE_NAME} WHERE ${DepartmentsContract.Colums.SHOP_ID}=:shopId")
    suspend fun getShopsAndDepartsFilter(shopId: Long): List<ShopsWithDepartments>

    @Insert
    suspend fun insertDepartments(departments: List<Departments>)

    @Delete
    suspend fun deleteDepartments(departments: Departments)

    @Query("DELETE FROM ${DepartmentsContract.TABLE_NAME} WHERE ${DepartmentsContract.Colums.ID}=:departmentsId")
    suspend fun deleteDepartmentsById(departmentsId: Long)

    @Query("SELECT * FROM ${DepartmentsContract.TABLE_NAME} WHERE ${DepartmentsContract.Colums.ID}=:departmentsId")
    suspend fun getDepartmentsById(departmentsId: Long): Departments?
}
