package com.skilbox.shopdb.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import timber.log.Timber

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        Timber.d("migration 1-2 start")
        database.execSQL("ALTER TABLE Sale ADD COLUMN status TEXT NOT NULL DEFAULT 'FINISHED'")
        Timber.d("migration 1-2 end")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        Timber.d("migration 2-3 start")
        database.execSQL("ALTER TABLE supply ADD COLUMN status TEXT NOT NULL DEFAULT 'FINISHED'")
        Timber.d("migration 2-3 end")
    }
}
