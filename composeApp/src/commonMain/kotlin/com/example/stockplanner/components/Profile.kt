package com.example.stockplanner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.stockplanner.utils.uiComponents.Buttons
@Composable
fun Profile(
    appState: AppState
) {
    val quote by appState.quote.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(AppThemeValues.spacing.small)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(AppThemeValues.spacing.small),
    ) {
        // User Avatar + Name
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(AppThemeValues.shapes.small)
                .background(AppThemeValues.colors.primary.copy(alpha = 0.1f))
                .padding(AppThemeValues.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppThemeValues.spacing.small)
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(AppThemeValues.colors.primary.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = appState.userName.take(1).uppercase(),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppThemeValues.colors.primary
                )
            }
            Text(
                text = appState.userName,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }

        // Funds Card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(AppThemeValues.shapes.small)
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(AppThemeValues.spacing.small),
            verticalArrangement = Arrangement.spacedBy(AppThemeValues.spacing.small)
        ) {
            Text(
                text = "Funds",
                fontSize = 12.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "₹99,99,999",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        // Quote Card
        quote?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppThemeValues.shapes.small)
                    .background(Color.Gray.copy(alpha = 0.1f))
                    .padding(AppThemeValues.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(AppThemeValues.spacing.small)
            ) {
                Text(
                    text = "❝",
                    fontSize = 24.sp,
                    color = AppThemeValues.colors.primary
                )
                Text(
                    text = it.quote,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp
                )
                Text(
                    text = "— ${it.author}",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Logout Button
        Buttons(
            text = "Logout",
            onClick = { appState.logout() },
            severity = "error",
            modifier = Modifier.fillMaxWidth()
        )
    }
}