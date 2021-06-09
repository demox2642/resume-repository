package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EmployersDao {

    @Query("SELECT * FROM ${EmployersContract.TABLE_NAME}")
    suspend fun getAllEmployers(): List<Employers>

    @Insert
    suspend fun insertEmployers(employers: List<Employers>)

    @Delete
    suspend fun deleteEmployers(employers: Employers)

    @Query("DELETE FROM ${EmployersContract.TABLE_NAME} WHERE ${EmployersContract.Colums.ID}=:employersId")
    suspend fun deleteEmployersById(employersId: Long)

    @Query("SELECT * FROM ${EmployersContract.TABLE_NAME} WHERE ${EmployersContract.Colums.ID}=:employersId")
    suspend fun getEmployersById(employersId: Long): Employers?

    @Query(
        "SELECT emp.surname, emp.name, emp.middle_name, emp.birthday, emp.id,p.name as position_name,\"магазин: \"||s.name||\" отдел: \"|| d.full_name|| (case when parent.full_name is null then \"\"else \" родительский отдел: \\\"||parent.full_name end)as plase\n" +
            "FROM employers emp\n" +
            "INNER JOIN linc_dept_emp_post linc on linc.employer_id=emp.id\n" +
            "INNER JOIN positions p on linc.position_id=p.id\n" +
            "INNER JOIN DEPARTMENTS d on p.dept_id=d.id\n" +
            "LEFT JOIN DEPARTMENTS parent on d.parent_id=parent.id\n" +
            "INNER JOIN SHOPS s on d.shop_id=s.id"
    )
    suspend fun getEmploersPositionPlace(): List<EmploersPositionPlace>

    @Query("DELETE FROM ${EmployersContract.TABLE_NAME}")
    suspend fun cleanEmployerList()

    @Query("SELECT * FROM employers emp WHERE emp.surname=:surname AND emp.name=:name AND emp.middle_name=:middle_name ")
    suspend fun findEmployer(
        surname: String,
        name: String,
        middle_name: String
    ): Employers
}
