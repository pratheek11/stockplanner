package com.example.stockplanner.database

import com.example.stockplanner.db.AppDatabase
import app.cash.sqldelight.db.SqlDriver

expect class DatabaseFactory() {
    fun createDriver(): SqlDriver?
}

fun createAppDatabase(factory: DatabaseFactory): AppDatabase? {
    val driver = factory.createDriver() ?: return null
    return AppDatabase(driver)
}
