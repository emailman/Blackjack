package edu.emailman.blackjack.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val BlackjackDarkColorScheme = darkColorScheme(
    primary = BlackjackColors.Gold,
    onPrimary = Color.Black,
    primaryContainer = BlackjackColors.FeltGreen,
    onPrimaryContainer = BlackjackColors.TextOnFelt,
    secondary = BlackjackColors.ChipRed,
    secondaryContainer = BlackjackColors.FeltGreenDark,
    background = BlackjackColors.FeltGreen,
    surface = BlackjackColors.FeltGreenDark,
    onBackground = BlackjackColors.TextOnFelt,
    onSurface = BlackjackColors.TextOnFelt
)

@Composable
fun BlackjackTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = BlackjackDarkColorScheme,
        typography = Typography(),
        content = content
    )
}
