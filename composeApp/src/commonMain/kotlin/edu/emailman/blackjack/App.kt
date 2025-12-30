package edu.emailman.blackjack

import androidx.compose.runtime.Composable
import edu.emailman.blackjack.ui.BlackjackScreen
import edu.emailman.blackjack.ui.theme.BlackjackTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    BlackjackTheme {
        BlackjackScreen()
    }
}
