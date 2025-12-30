package edu.emailman.blackjack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.emailman.blackjack.model.GameResult
import edu.emailman.blackjack.ui.theme.BlackjackColors

@Composable
fun ResultOverlay(
    result: GameResult,
    message: String,
    onNewRound: () -> Unit,
    onQuit: () -> Unit,
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    if (!visible) return

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { }
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.padding(32.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = BlackjackColors.FeltGreenDark
            )
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val (resultText, resultColor) = when (result) {
                    GameResult.PLAYER_BLACKJACK -> "BLACKJACK!" to BlackjackColors.Gold
                    GameResult.PLAYER_WIN -> "YOU WIN!" to BlackjackColors.ResultWin
                    GameResult.DEALER_WIN -> "DEALER WINS" to BlackjackColors.ResultLose
                    GameResult.PUSH -> "PUSH" to BlackjackColors.ResultPush
                    GameResult.NONE -> "" to Color.White
                }

                Text(
                    text = resultText,
                    color = resultColor,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = message,
                    color = BlackjackColors.TextOnFelt,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { onNewRound() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BlackjackColors.Gold
                        )
                    ) {
                        Text("NEW HAND", color = Color.Black, maxLines = 1)
                    }

                    Button(
                        onClick = { onQuit() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BlackjackColors.ChipRed
                        )
                    ) {
                        Text("QUIT", color = Color.White)
                    }
                }
            }
        }
    }
}
