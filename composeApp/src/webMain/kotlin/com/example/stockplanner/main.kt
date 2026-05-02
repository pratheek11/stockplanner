package com.example.stockplanner

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.example.stockplanner.database.AppDatabaseRepository
import com.example.stockplanner.database.DatabaseFactory
import com.example.stockplanner.database.DatabaseProvider
import com.example.stockplanner.utils.models.AppState
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsModule

@OptIn(ExperimentalWasmJsInterop::class)
@JsModule("@js-joda/timezone")
external object JsJodaTimeZoneModule

private val jsJodaTz = JsJodaTimeZoneModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    AppDatabaseRepository.initialize(DatabaseFactory())

    val databaseManager = DatabaseProvider.manager

    ComposeViewport {
        App(AppState(databaseManager))
    }
}