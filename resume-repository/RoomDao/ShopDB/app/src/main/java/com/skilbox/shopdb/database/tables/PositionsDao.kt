package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PositionsDao {
    @Query("SELECT * FROM ${PositionsContract.TABLE_NAME}")
    suspend fun getAllPositions(): List<Positions>

    @Insert
    suspend fun insertPositions(positionsId: List<Positions>)

    @Delete
    suspend fun deletePositions(positionsId: Positions)

    @Query("DELETE FROM ${PositionsContract.TABLE_NAME} WHERE ${PositionsContract.Colums.ID}=:positionsId")
    suspend fun deletePositionsById(positionsId: Long)

    @Query("SELECT * FROM ${PositionsContract.TABLE_NAME} WHERE ${PositionsContract.Colums.ID}=:positionsId")
    suspend fun getPositionsById(positionsId: Long): Positions

    @Query(
        "select p.id,p.name,\"магазин: \"||s.name||\" отдел: \"|| d.full_name||(case when parent.full_name is null then \"\" else \" родительский отдел: \"||parent.full_name end) as plase\n" +
            "from positions p\n" +
            "INNER JOIN DEPARTMENTS d on p.dept_id=d.id\n" +
            "LEFT JOIN DEPARTMENTS parent on d.parent_id=parent.id\n" +
            "INNER JOIN SHOPS s on d.shop_id=s.id"
    )
    suspend fun getPositionWhithPlace(): List<PositionWhithPlace>

    @Query(
        "select p.id ,p.name, (\"магазин: \"||s.name||\" отдел: \"|| d.full_name|| (case when parent.full_name is null then \"\"else \" родительский отдел: \\\"||parent.full_name end) )as plase\n" +
            "            from positions p\n" +
            "            INNER JOIN DEPARTMENTS d on p.dept_id=d.id\n" +
            "            LEFT JOIN DEPARTMENTS parent on d.parent_id=parent.id\n" +
            "            INNER JOIN SHOPS s on d.shop_id=s.id\n" +
            "            WHERE p.name=:positionName"
    )
    suspend fun getSearchPlaseByPosition(positionName: String): List<PositionWhithPlace>

    @Query("DELETE FROM ${PositionsContract.TABLE_NAME}")
    suspend fun clianPositionList()
}
