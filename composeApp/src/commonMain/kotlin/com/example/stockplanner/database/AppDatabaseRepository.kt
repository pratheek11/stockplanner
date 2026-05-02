package com.example.stockplanner.database

import com.example.stockplanner.db.AppDatabase
import kotlinx.coroutines.Dispatchers

object AppDatabaseRepository {
    private var database: AppDatabase? = null

    fun initialize(factory: DatabaseFactory) {
        if (database == null) {
            database = createAppDatabase(factory)
        }
    }

    fun getInstance(): AppDatabase {
        requireNotNull(database) {
            "AppDatabase not initialized. Call initialize(factory) first"
        }
        return database!!
    }
}

val databaseDispatcher = Dispatchers.Default
