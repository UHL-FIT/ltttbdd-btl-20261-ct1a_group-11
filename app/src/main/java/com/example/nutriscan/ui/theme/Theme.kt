package com.example.nutriscan.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = GreenTertiary,
    secondary = GreenSecondary,
    tertiary = GreenPrimary,
    background = Color(0xFF101510),
    surface = Color(0xFF101510),
    primaryContainer = Color(0xFF203820)
)

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    secondary = GreenSecondary,
    tertiary = GreenTertiary,
    background = GreenBackground,
    surface = GreenBackground,
    primaryContainer = GreenContainer
)

@Composable
fun NutriScanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val nutriAppColors = getNutriAppColors(darkTheme)

    CompositionLocalProvider(LocalNutriAppColors provides nutriAppColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}