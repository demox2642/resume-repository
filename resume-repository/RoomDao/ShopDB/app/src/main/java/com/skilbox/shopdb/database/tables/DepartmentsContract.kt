package com.skilbox.shopdb.database.tables

object DepartmentsContract {
    const val TABLE_NAME = "departments"

    object Colums {

        const val ID = "id"
        const val PARENT_ID = "parent_id"
        const val SHOP_ID = "shop_id"
        const val FULL_NAME = "full_name"
        const val SHORT_NAME = "short_name"
    }
}
