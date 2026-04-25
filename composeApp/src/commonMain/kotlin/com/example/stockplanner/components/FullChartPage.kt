package com.example.stockplanner.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.models.ChartScreen
import com.example.stockplanner.utils.theme.AppThemeValues

@Composable
fun FullChartPage(
    appState: AppState
) {
    val currentItem by appState.currentFullChartItem.collectAsState()
    LaunchedEffect(Unit) {
        appState.loadChartData("NSE_EQ|INE839G01010")
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppThemeValues.spacing.small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "<-",
                color = Color.Black,
                modifier = Modifier
                    .clip(AppThemeValues.shapes.small)
                    .clickable{ appState.setChartScreen(ChartScreen.ChartList) }
                    .padding(AppThemeValues.spacing.small)
            )
            Text(
                text = currentItem.id
            )
        }
    }
}