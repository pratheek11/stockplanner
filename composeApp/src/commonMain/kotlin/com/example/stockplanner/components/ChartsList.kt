package com.example.stockplanner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.models.ChartScreen
import com.example.stockplanner.utils.theme.AppThemeValues
import com.example.stockplanner.utils.uiComponents.InputBox

@Composable
fun ChartsList(
    appState: AppState,
) {
    val currentList by appState.currentList.collectAsState()
    val chartListState = appState.chartItemByList[currentList.id]

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(AppThemeValues.spacing.small),
    ) {
        InputBox(
            label = "Search",
            onTextChange = {},
            value = "",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppThemeValues.spacing.small)
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(
                AppThemeValues.spacing.small
            )
        ){
            if (chartListState != null) {
                for( item in chartListState){
                    Column (
                        modifier = Modifier
                            .clip(AppThemeValues.shapes.small)
                            .fillMaxWidth()
                            .clickable {
                                appState.setChartScreen(ChartScreen.FullChart)
                            }
                            .height(AppThemeValues.spacing.medium * 4)
                            .background(Color.Gray.copy(alpha = 0.1f)),
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(AppThemeValues.spacing.small)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = item.id,
                            )
                            Text(
                                text = item.lastPrice.toString(),
                            )
                        }
                    }
                }
            }
        }
    }
}