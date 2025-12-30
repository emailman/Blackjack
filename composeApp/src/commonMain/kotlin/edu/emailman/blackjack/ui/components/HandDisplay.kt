package edu.emailman.blackjack.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.emailman.blackjack.model.Hand
import edu.emailman.blackjack.ui.theme.BlackjackColors

@Composable
fun HandDisplay(
    hand: Hand,
    label: String,
    showValue: Boolean = true,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = BlackjackColors.TextOnFelt,
            fontSize = 18.sp
        )

        if (showValue && hand.cards.isNotEmpty()) {
            val displayValue = if (hand.cards.all { it.isFaceUp }) {
                hand.bestValue.toString()
            } else {
                hand.visibleValue.toString()
            }
            Text(
                text = "Value: $displayValue",
                color = BlackjackColors.Gold,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy((-30).dp)
        ) {
            hand.cards.forEachIndexed { index, card ->
                CardComposable(
                    card = card,
                    animateDeal = true,
                    dealDelay = index * 150
                )
            }
        }
    }
}
