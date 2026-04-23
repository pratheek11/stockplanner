package com.example.stockplanner.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.models.ChartScreen

@Composable
fun ChartsHome(
    appState: AppState
) {
    val currentChartScreen by appState.currentChartScreen.collectAsState()
    when (currentChartScreen) {
        ChartScreen.ChartList -> {
            ChartsMenuList(appState)
            ChartsList(appState)
        }
        ChartScreen.FullChart -> FullChartPage(appState)
    }
}