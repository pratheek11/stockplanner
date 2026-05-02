package com.example.stockplanner.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.example.stockplanner.db.AppDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun createDriver(): SqlDriver? {
        val dbFile = File(System.getProperty("user.home"), ".stockplanner/stockplanner.db")
        dbFile.parentFile?.mkdirs()
        val driver = JdbcSqliteDriver("jdbc:sqlite:${dbFile.absolutePath}")
        AppDatabase.Schema.create(driver)
        return driver
    }
}