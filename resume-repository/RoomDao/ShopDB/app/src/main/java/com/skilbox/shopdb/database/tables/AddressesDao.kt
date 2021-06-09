package com.skilbox.shopdb.database.tables

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AddressesDao {
    @Query("SELECT * FROM ${AddressesContract.TABLE_NAME}")
    suspend fun getAllAddresses(): List<Addresses>

    @Insert
    suspend fun insertAddresses(addresses: List<Addresses>)

    @Delete
    suspend fun deleteAddresses(address: Addresses)

    @Query("DELETE FROM ${AddressesContract.TABLE_NAME} WHERE ${AddressesContract.Colums.ID}=:addressId")
    suspend fun deleteAddressesById(addressId: Long)

    @Query("SELECT * FROM ${AddressesContract.TABLE_NAME} WHERE ${AddressesContract.Colums.ID}=:addressId")
    suspend fun getAddressesById(addressId: Long): Addresses?

    //
//    @Query("SELECT * FROM ${AddressesContract.TABLE_NAME}")
//    suspend fun getAllAddresses():List<Addresses>
//
//    @Insert
//    suspend fun insertAddresses(addresses: List<Addresses>)
//
//    @Delete
//    suspend fun deleteAddresses(address:Addresses)
//
    @Query("DELETE FROM ${AddressesContract.TABLE_NAME}")
    suspend fun deleteAllAddresses()

    //
//    @Query("DELETE FROM ${AddressesContract.TABLE_NAME} WHERE ${AddressesContract.Colums.ID}=:addressId")
//    suspend fun deleteAddressesById(addressId:Long)
//
//    @Query("SELECT * FROM ${AddressesContract.TABLE_NAME} WHERE ${AddressesContract.Colums.ID}=:addressId")
//    suspend fun getAddressesById(addressId:Long):Addresses?
//
    @Query("SELECT COUNT(*) FROM ${AddressesContract.TABLE_NAME} WHERE ${AddressesContract.Colums.CITY}||${AddressesContract.Colums.STREET}||${AddressesContract.Colums.BUILDING}=:sity||:street||:building")
    suspend fun checkAddresses(sity: String, street: String, building: String): Int
}
