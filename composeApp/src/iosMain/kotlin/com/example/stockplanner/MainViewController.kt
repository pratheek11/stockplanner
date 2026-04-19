package com.example.stockplanner

import androidx.compose.ui.window.ComposeUIViewController
import com.example.stockplanner.utils.models.AppState

fun MainViewController() = ComposeUIViewController { App(AppState()) }