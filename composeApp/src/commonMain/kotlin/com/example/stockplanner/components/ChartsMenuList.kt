package com.example.stockplanner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.models.ChartHeaderList
import com.example.stockplanner.utils.theme.AppThemeValues

@Composable
fun ChartsMenuList(
    appState: AppState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(AppThemeValues.spacing.small),
    ) {
        Column(
            modifier = Modifier
                .clip(AppThemeValues.shapes.small)
                .clickable {

                }
                .padding(AppThemeValues.spacing.small)
        ) {
            Text(
                text = "+",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(Color.Gray.copy(alpha = 0.3f))
        )
        Row(
            modifier = Modifier
                .weight(1f)
                .horizontalScroll(rememberScrollState()),
        ) {
            val currentList by appState.currentList.collectAsState()
            repeat(2){
                Column(
                    modifier = Modifier
                        .clip(AppThemeValues.shapes.small)
                        .clickable {
                            appState.setCurrentList(ChartHeaderList("List $it", "$it$it"))
                        }
                        .padding(AppThemeValues.spacing.small)
                ) {
                    Text(
                        text = "List $it",
                        fontWeight = if (currentList.id == "$it$it") FontWeight.Bold else FontWeight.Normal,
                        color = if (currentList.id == "$it$it") AppThemeValues.colors.primary else Color.Black,
                    )
                }
            }
        }
    }
}