package com.example.stockplanner.components

import androidx.compose.runtime.Composable
import com.example.stockplanner.utils.models.AppState

@Composable
fun ChartsHome(
    appState: AppState
) {
    ChartsList(appState)
}