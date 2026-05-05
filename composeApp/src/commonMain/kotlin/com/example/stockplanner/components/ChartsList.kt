package com.example.stockplanner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockplanner.db.Watchlist
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.models.ChartScreen
import com.example.stockplanner.utils.services.StockList
import com.example.stockplanner.utils.theme.AppThemeValues
import com.example.stockplanner.utils.uiComponents.Buttons
import com.example.stockplanner.utils.uiComponents.InputBox
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ChartsList(
    appState: AppState,
) {
    val currentList by appState.currentList.collectAsState()
    val currentWatchlist by appState.chartItemByList.collectAsState()
    var searchChart by remember { mutableStateOf("") }
    val searchResults by appState.searchResults.collectAsState()

    LaunchedEffect(currentList?.id) {
        appState.getItemFromChartListByList(currentList?.id + "")
    }

    val displayList: List<Watchlist> = if (searchResults.isEmpty())
        currentWatchlist[currentList?.id] ?: emptyList()
    else
        emptyList()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(AppThemeValues.spacing.small),
    ) {
        InputBox(
            label = "Search stocks...",
            onTextChange = { newText ->
                searchChart = newText
                appState.getByKey(newText)
            },
            value = searchChart,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(AppThemeValues.spacing.small))

        // Section Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (searchResults.isNotEmpty()) "Results" else currentList?.label ?: "Watchlist",
                fontSize = 12.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = if (searchResults.isNotEmpty()) "${searchResults.size} found" else "${displayList.size} stocks",
                fontSize = 12.sp,
                color = Color.Gray,
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(AppThemeValues.spacing.small)
        ) {
            // Search Results - List<StockList>
            searchResults.forEach { item: StockList ->
                SearchResultItem(
                    item = item,
                    onItemClick = {
                        appState.setChartScreen(ChartScreen.FullChart)
                        appState.setCurrentFullChart(item)
                    },
                    onAddClick = {
                        appState.insertItemIntoChartList(currentList?.id + "", item)
                    }
                )
            }

            // Watchlist Items - List<Watchlist>
            if (displayList.isEmpty() && searchResults.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "📋", fontSize = 40.sp)
                    Text(
                        text = "No stocks in this watchlist",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Search above to add stocks",
                        fontSize = 12.sp,
                        color = Color.Gray.copy(alpha = 0.7f)
                    )
                }
            } else {
                displayList.forEach { item: Watchlist ->
                    WatchlistItem(
                        item = item,
                        onItemClick = {
                            appState.setChartScreen(ChartScreen.FullChart)
                            appState.setCurrentFullChart(item)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchResultItem(
    item: StockList,
    onItemClick: () -> Unit,
    onAddClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(AppThemeValues.shapes.small)
            .fillMaxWidth()
            .clickable { onItemClick() }
            .background(Color.Gray.copy(alpha = 0.08f))
            .padding(AppThemeValues.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.trading_symbol,
                color = Color.Gray,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(AppThemeValues.shapes.small)
                    .background(Color.Gray.copy(alpha = 0.15f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = item.exchange,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }
            Buttons(
                modifier = Modifier,
                text = "+",
                onClick = { onAddClick() },
                severity = "secondary"
            )
        }
    }
}

@Composable
private fun WatchlistItem(
    item: Watchlist,
    onItemClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clip(AppThemeValues.shapes.small)
            .fillMaxWidth()
            .clickable { onItemClick() }
            .background(Color.Gray.copy(alpha = 0.08f))
            .padding(AppThemeValues.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.symbol,
                color = Color.Gray,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(
            modifier = Modifier
                .clip(AppThemeValues.shapes.small)
                .background(Color.Gray.copy(alpha = 0.15f))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = item.exchange,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
        }
    }
}