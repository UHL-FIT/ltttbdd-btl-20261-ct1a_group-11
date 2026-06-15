package com.example.nutriscan.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class NutriAppColors(
    val background: Color,
    val card: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val primary: Color,
    val warning: Color,
    val border: Color,
    val lightGreen: Color,
    val blue: Color,
    val orange: Color,
    val progressBackground: Color,
    val borderColor: Color
)

val LocalNutriAppColors = staticCompositionLocalOf<NutriAppColors> {
    error("No NutriAppColors provided")
}

fun getNutriAppColors(isDarkMode: Boolean): NutriAppColors {
    return if (isDarkMode) {
        NutriAppColors(
            background = Color(0xFF101510),
            card = Color(0xFF1B241B),
            textPrimary = Color.White,
            textSecondary = Color(0xFFBDBDBD),
            primary = Color(0xFF81C784),
            warning = Color(0xFFFF8A80),
            border = Color(0xFF3A4A3A),
            lightGreen = Color(0xFF203820),
            blue = Color(0xFF64B5F6),
            orange = Color(0xFFFFB74D),
            progressBackground = Color(0xFF2C2C2E),
            borderColor = Color(0xFF3A3A3C)
        )
    } else {
        NutriAppColors(
            background = Color(0xFFF1F8F4),
            card = Color.White,
            textPrimary = Color(0xFF1A1C1E),
            textSecondary = Color.Gray,
            primary = Color(0xFF2E7D32),
            warning = Color(0xFFD32F2F),
            border = Color(0xFFE0E0E0),
            lightGreen = Color(0xFFE8F5E9),
            blue = Color(0xFF2196F3),
            orange = Color(0xFFFF9800),
            progressBackground = Color(0xFFE0E0E0),
            borderColor = Color(0xFFEEEEEE)
        )
    }
}