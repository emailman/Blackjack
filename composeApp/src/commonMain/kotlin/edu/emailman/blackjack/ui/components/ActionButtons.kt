package edu.emailman.blackjack.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.emailman.blackjack.ui.theme.BlackjackColors

@Composable
fun ActionButtons(
    canHit: Boolean,
    canStand: Boolean,
    onHit: () -> Unit,
    onStand: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Button(
            onClick = onHit,
            enabled = canHit,
            colors = ButtonDefaults.buttonColors(
                containerColor = BlackjackColors.ChipGreen,
                disabledContainerColor = BlackjackColors.ChipGreen.copy(alpha = 0.4f)
            ),
            modifier = Modifier.width(120.dp)
        ) {
            Text("HIT", fontSize = 18.sp)
        }

        Button(
            onClick = onStand,
            enabled = canStand,
            colors = ButtonDefaults.buttonColors(
                containerColor = BlackjackColors.ChipRed,
                disabledContainerColor = BlackjackColors.ChipRed.copy(alpha = 0.4f)
            ),
            modifier = Modifier.width(120.dp)
        ) {
            Text("STAND", fontSize = 18.sp)
        }
    }
}
