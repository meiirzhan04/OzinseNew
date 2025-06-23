package com.example.ozinsenew.ui.theme


import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    background = Grey900,
    primary = Grey800,
    secondary = Grey700,
    tertiary = Grey600,
    surface = Grey500,

    onBackground = Red900,
    onPrimary = Red800,
    onSecondary = Red700,
    onTertiary = Red600,
    onSurface = Red500,

    onPrimaryContainer = Color.White,
    onSurfaceVariant = Grey400,
    onSecondaryContainer = Color.White,
    onTertiaryContainer = Grey800,

    primaryContainer = Grey700,
    secondaryContainer = Grey800,
    tertiaryContainer = Grey200,
    surfaceVariant = Grey700
)
private val LightColorScheme = lightColorScheme(
    background = Grey50,
    primary = Grey100,
    secondary = Grey200,
    tertiary = Grey300,
    surface = Grey400,

    onBackground = Red50,
    onPrimary = Red100,
    onSecondary = Red200,
    onTertiary = Red300,
    onSurface = Red400,

    onPrimaryContainer = Grey900,
    onSurfaceVariant = Grey500,
    onSecondaryContainer = Grey500,
    onTertiaryContainer = Color.White,

    primaryContainer = Grey300,
    secondaryContainer = Red50,
    tertiaryContainer = Grey400,
    surfaceVariant = Grey100
)


@Composable
fun OzinseNewTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) DarkColorScheme else LightColorScheme
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val colors = colorScheme
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}