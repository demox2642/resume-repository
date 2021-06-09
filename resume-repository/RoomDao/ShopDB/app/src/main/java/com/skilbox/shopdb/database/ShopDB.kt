package com.skilbox.shopdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.skilbox.shopdb.database.ShopDB.Companion.DB_VERSION
import com.skilbox.shopdb.database.tables.*

@Database(
    entities = [
        Addresses::class,
        Departments::class,
        Employers::class,
        Linc_Dept_Emp_Pos::class,
        Positions::class,
        Products::class,
        Products_Unit::class,
        Sale::class,
        Sale_Detail::class,
        Sale_Price::class,
        Shops::class,
        Suppliers::class,
        Supplies_Price::class,
        Supply_Detail::class,
        Supply::class,
        Warehouse::class
    ],
    version = DB_VERSION
)

@TypeConverters(PurchaseDateConverter::class, PurchaseBigDecimalConverter::class)

abstract class ShopDB : RoomDatabase() {

    abstract fun addressesDao(): AddressesDao
    abstract fun departmentsDao(): DepartmentsDao
    abstract fun employersDao(): EmployersDao
    abstract fun lincDeptEmpPosDao(): Linc_Dept_Emp_PostDao
    abstract fun positionDao(): PositionsDao
    abstract fun productDao(): ProductsDao
    abstract fun productUnitDao(): Products_UnitDao
    abstract fun saleDao(): SaleDao
    abstract fun saleDetailDao(): Sale_DetailDao
    abstract fun salePriceDao(): Sale_PriceDao
    abstract fun shopsDao(): ShopsDao
    abstract fun suppliersDao(): SuppliersDao
    abstract fun suppliersPriceDao(): Supplies_PriceDao
    abstract fun supplyDetailDao(): Supply_DetailDao
    abstract fun supplyDao(): SupplyDao
    abstract fun warehouseDao(): WarehouseDao

    companion object {
        const val DB_VERSION = 3
        const val DB_NAME = "ShopDB"
    }
}
