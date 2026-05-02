package com.example.stockplanner

import androidx.compose.ui.window.ComposeUIViewController
import com.example.stockplanner.database.AppDatabaseRepository
import com.example.stockplanner.database.DatabaseFactory
import com.example.stockplanner.database.DatabaseProvider
import com.example.stockplanner.utils.models.AppState

fun MainViewController() = ComposeUIViewController {
    DatabaseProvider.initialize(factory = DatabaseFactory())
    val databaseManager = DatabaseProvider.manager
    App(AppState(databaseManager))
}