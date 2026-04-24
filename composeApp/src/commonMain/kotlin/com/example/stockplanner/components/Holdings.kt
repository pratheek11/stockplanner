package com.example.stockplanner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.theme.AppThemeValues

@Composable
fun Holdings(
    appState: AppState
) {
    val holdings by appState.holdings.collectAsState()
    val currentPositions by appState.currentPositions.collectAsState()
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(AppThemeValues.spacing.small)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppThemeValues.spacing.large * 8)
                .clip(AppThemeValues.shapes.small)
                .background(Color.Gray.copy(alpha = 0.1f)),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppThemeValues.spacing.small),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Invested"
                )
                Text(
                    text = "${holdings.invested}"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppThemeValues.spacing.small),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Current",
                )
                Text(
                    text = "${holdings.current}",
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .height(1.dp)
                    .padding(AppThemeValues.spacing.small)
                    .clip(AppThemeValues.shapes.small)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppThemeValues.spacing.small),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "",
                )
                Text(
                    text = "",
                )
            }
        }
    }
}