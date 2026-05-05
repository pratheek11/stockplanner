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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.models.ChartHeaderList
import com.example.stockplanner.utils.theme.AppThemeValues

@Composable
fun ChartsMenuList(
    appState: AppState,
) {
    LaunchedEffect(Unit) {
        appState.getChartHeaderList()
    }

    val currentItem by appState.currentList.collectAsState()
    val currentList by appState.chartHeaderList.collectAsState()

    // Auto-select first item when list loads
    LaunchedEffect(currentList) {
        if (currentItem == null && currentList.isNotEmpty()) {
            val first = currentList.first()
            appState.setCurrentList(ChartHeaderList(first.label, first.id))
            appState.getItemFromChartListByList(first.id)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
            .padding(horizontal = AppThemeValues.spacing.small),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        // Add Button
        Box(
            modifier = Modifier
                .clip(AppThemeValues.shapes.small)
                .background(AppThemeValues.colors.primary.copy(alpha = 0.1f))
                .clickable {
                    appState.addChartHeaderList("List ${appState.chartHeaderList.value.size + 1}")
                }
                .padding(horizontal = 12.dp, vertical = 6.dp),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text(
                text = "+",
                fontWeight = FontWeight.Bold,
                color = AppThemeValues.colors.primary,
                fontSize = 18.sp
            )
        }

        // Divider
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxHeight(0.6f)
                .width(1.dp)
                .background(Color.Gray.copy(alpha = 0.3f))
        )

        // Tabs
        Row(
            modifier = Modifier
                .weight(1f)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            currentList.forEach { item ->
                val isSelected = currentItem?.id == item.id
                Column(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .clip(AppThemeValues.shapes.small)
                        .background(
                            if (isSelected) AppThemeValues.colors.primary.copy(alpha = 0.1f)
                            else Color.Transparent
                        )
                        .clickable {
                            appState.setCurrentList(ChartHeaderList(item.label, item.id))
                            // Load list immediately on click
                            appState.getItemFromChartListByList(item.id)
                        }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    Text(
                        text = item.label,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) AppThemeValues.colors.primary else Color.Gray,
                        fontSize = 14.sp
                    )
                    // Active indicator
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .width(16.dp)
                                .height(2.dp)
                                .clip(androidx.compose.foundation.shape.RoundedCornerShape(1.dp))
                                .background(AppThemeValues.colors.primary)
                        )
                    }
                }
            }
        }
    }
}