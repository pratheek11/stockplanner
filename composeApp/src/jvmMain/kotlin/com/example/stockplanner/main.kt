package com.example.stockplanner

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.stockplanner.utils.models.AppState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "stockplanner",
    ) {
        App(AppState())
    }
}