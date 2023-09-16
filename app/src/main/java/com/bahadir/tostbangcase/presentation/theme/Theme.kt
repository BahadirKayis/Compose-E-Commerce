package com.bahadir.tostbangcase.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple40,
    primaryVariant = PurpleGrey40,
    secondary = Pink40,
)

private val LightColorPalette = lightColors(
    primary = Purple80,
    primaryVariant = PurpleGrey80,
    secondary = Pink80,
    onPrimary = Purple40,
    onSecondary = Blue,

)

@Composable
fun FiriyaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    androidx.compose.material.MaterialTheme(
        colors = colors,
        content = content,
    )
}
