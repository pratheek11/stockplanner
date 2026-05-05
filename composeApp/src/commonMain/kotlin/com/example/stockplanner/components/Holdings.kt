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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockplanner.utils.models.AppState
import com.example.stockplanner.utils.theme.AppThemeValues

@Composable
fun Holdings(
    appState: AppState
) {
    val holdings by appState.holdings.collectAsState()
    val currentPositions by appState.currentPositions.collectAsState()

    fun formatAmount(value: Double): String {
        return (kotlin.math.round(value * 100) / 100.0).toString()
    }

    val pnl = holdings.current.toDouble() - holdings.invested.toDouble()
    val pnlPercent = if (holdings.invested.toDouble() > 0)
        (pnl / holdings.invested.toDouble()) * 100 else 0.0
    val isProfit = pnl >= 0
    val pnlColor = if (isProfit) Color(0xFF00C853) else Color(0xFFD50000)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(AppThemeValues.spacing.small)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(AppThemeValues.spacing.small)
    ) {
        // Summary Card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(AppThemeValues.shapes.small)
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(AppThemeValues.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(AppThemeValues.spacing.small)
        ) {
            Text(
                text = "Portfolio Summary",
                fontSize = 12.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))

            // Invested
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Invested",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "₹${holdings.invested}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Current
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Current",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "₹${holdings.current}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Divider
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray.copy(alpha = 0.2f))
            )

            // P&L
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "P&L",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "${if (isProfit) "+" else ""}₹${formatAmount(pnl)}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = pnlColor
                    )
                    Text(
                        text = "${formatAmount(pnlPercent)}%",
                        fontSize = 12.sp,
                        color = pnlColor
                    )
                }
            }
        }

        // Positions List
        if (currentPositions.isNotEmpty()) {
            Text(
                text = "Positions",
                fontSize = 12.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
            currentPositions.forEach { position ->
                val positionPnl = position.currentTradedAmount - position.investedTradedAmount
                val positionPnlColor = if (positionPnl >= 0) Color(0xFF00C853) else Color(0xFFD50000)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(AppThemeValues.shapes.small)
                        .background(Color.Gray.copy(alpha = 0.1f))
                        .padding(AppThemeValues.spacing.small),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Qty: ${position.quantityInStock}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Invested: ₹${formatAmount(position.investedTradedAmount)}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "₹${formatAmount(position.currentTradedAmount)}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "${if (positionPnl >= 0) "+" else ""}₹${formatAmount(positionPnl)}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = positionPnlColor
                        )
                    }
                }
            }
        } else {
            // Empty state
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppThemeValues.shapes.small)
                    .background(Color.Gray.copy(alpha = 0.1f))
                    .padding(AppThemeValues.spacing.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No positions yet",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}