package com.example.stockplanner.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.stockplanner.db.AppDatabase

actual class DatabaseFactory {
    actual fun createDriver(): SqlDriver? {
        return NativeSqliteDriver(AppDatabase.Schema, "stockplanner.db")
    }
}
