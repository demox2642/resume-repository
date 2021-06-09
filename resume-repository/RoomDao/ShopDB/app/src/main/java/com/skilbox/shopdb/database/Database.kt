package com.skilbox.shopdb.database

import android.content.Context
import androidx.room.Room

object Database {

    lateinit var instance: ShopDB
        private set

    fun init(context: Context) {
        instance = Room.databaseBuilder(
            context,
            ShopDB::class.java,
            ShopDB.DB_NAME
        )
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .fallbackToDestructiveMigration()
            .build()
    }
}
