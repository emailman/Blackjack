package edu.emailman.blackjack.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.emailman.blackjack.ui.theme.BlackjackColors

@Composable
fun BettingControls(
    currentBet: Int,
    playerChips: Int,
    onBetChange: (Int) -> Unit,
    onDeal: () -> Unit,
    onQuit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your Chips: $$playerChips",
            color = BlackjackColors.Gold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Current Bet: $$currentBet",
            color = BlackjackColors.TextOnFelt,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        ChipSelector(
            selectedAmount = currentBet,
            playerChips = playerChips,
            onChipSelected = onBetChange
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = onDeal,
                enabled = currentBet > 0 && currentBet <= playerChips,
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlackjackColors.Gold,
                    contentColor = BlackjackColors.CardBlack
                ),
                modifier = Modifier.width(100.dp)
            ) {
                Text("DEAL", fontSize = 18.sp)
            }

            Button(
                onClick = onQuit,
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlackjackColors.ChipRed
                ),
                modifier = Modifier.width(100.dp)
            ) {
                Text("QUIT", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}
