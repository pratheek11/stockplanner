package com.example.stockplanner

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.stockplanner.database.AppDatabaseRepository
import com.example.stockplanner.database.DatabaseFactory
import com.example.stockplanner.utils.models.AppState

fun main() = application {
    AppDatabaseRepository.initialize(factory = DatabaseFactory())
    Window(
        onCloseRequest = ::exitApplication,
        title = "stockplanner",
    ) {
        App(AppState())
    }
}