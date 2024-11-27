package com.example.exam.ui.theme

import android.app.Activity
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

// Dark Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),
    onPrimary = Color.White,
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    background = Color(0xFF121212),
    surface = Color(0xFF1F1B24),
    onBackground = Color.White,
    onSurface = Color.White
)

// Light Color Scheme
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    onPrimary = Color.White,
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun ExamTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Default is to follow system theme
    dynamicColor: Boolean = true,  // For dynamic color on Android 12+
    content: @Composable () -> Unit
) {
    // Get the current context to apply dynamic color schemes on Android 12+
    val context = LocalContext.current

    // Determine the appropriate color scheme
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // For devices running Android 12+ (API 31 or higher), use dynamic color scheme
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme  // Fallback to dark color scheme if system is dark
        else -> LightColorScheme  // Otherwise, use light color scheme
    }

    // Apply the selected color scheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,  // Ensure typography is also applied
        content = content  // Pass down content
    )
}
