package edu.emailman.blackjack.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import edu.emailman.blackjack.model.Statistics
import edu.emailman.blackjack.ui.theme.BlackjackColors

@Composable
fun StatisticsPanel(
    statistics: Statistics,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatItem("Wins", statistics.wins.toString())
        StatItem("Losses", statistics.losses.toString())
        StatItem("Pushes", statistics.pushes.toString())
        StatItem("Blackjacks", statistics.blackjacks.toString())
        StatItem("Win Rate", "${(statistics.winRate * 100).toInt()}%")
    }
}

@Composable
private fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            color = BlackjackColors.Gold,
            fontSize = 18.sp
        )
        Text(
            text = label,
            color = BlackjackColors.TextOnFelt.copy(alpha = 0.7f),
            fontSize = 14.sp
        )
    }
}
