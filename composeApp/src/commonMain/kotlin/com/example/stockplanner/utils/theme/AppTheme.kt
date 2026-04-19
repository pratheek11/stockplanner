package com.example.stockplanner.utils.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppColors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val warning: Color,
    val success: Color,
    val error: Color,
    val tabSelected: Color,
    val tabUnSelected: Color,
    val textLight: Color,
    val textDark: Color,
    val focusedContainerColor: Color,
    val unfocusedContainerColor: Color,
    val disabledContainerColor: Color,
    val disabled: Color
)

data class AppSpacing(
    val small: Dp,
    val medium: Dp,
    val large: Dp
)

data class AppShapes(
    val small: RoundedCornerShape,
    val medium: RoundedCornerShape,
    val large: RoundedCornerShape
)

val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No AppColors provided")
}

val LocalAppSpacing = staticCompositionLocalOf<AppSpacing> {
    error("No AppSpacing provided")
}

val LocalAppShapes = staticCompositionLocalOf<AppShapes> {
    error("No AppShapes provided")
}

object AppThemeValues {
    val colors: AppColors
        @Composable get() = LocalAppColors.current

    val spacing: AppSpacing
        @Composable get() = LocalAppSpacing.current

    val shapes: AppShapes
        @Composable get() = LocalAppShapes.current
}

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colors = AppColors(
        primary = Color(0x8037C0FF),
        secondary = Color(0xFFE7E7E7),
        background = Color(0xFFF5F5F5),
        warning = Color(0xFFFF692A),
        success = Color(0xFF25FF40),
        error = Color(0xfff44336),
        tabSelected = Color(0x8092D9FF),
        tabUnSelected = Color(0x40EEEEEE),
        textLight = Color.White,
        textDark = Color.Black,
        focusedContainerColor = Color(0xFFFFFFFF),
        unfocusedContainerColor = Color(0xFFFFFFFF),
        disabledContainerColor = Color(0xFFEEEEEE),
        disabled = Color(0x80E2E2E2)
    )

    val spacing = AppSpacing(
        small = 8.dp,
        medium = 16.dp,
        large = 24.dp
    )

    val shapes = AppShapes(
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(12.dp),
        large = RoundedCornerShape(20.dp)
    )

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppSpacing provides spacing,
        LocalAppShapes provides shapes
    ) {
        MaterialTheme {
            content()
        }
    }
}