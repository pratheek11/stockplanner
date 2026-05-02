package com.example.stockplanner.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.stockplanner.appContext
import com.example.stockplanner.db.AppDatabase

actual class DatabaseFactory {
    actual fun createDriver(): SqlDriver? {
        return AndroidSqliteDriver(AppDatabase.Schema, appContext, "stockplanner.db")
    }
}
