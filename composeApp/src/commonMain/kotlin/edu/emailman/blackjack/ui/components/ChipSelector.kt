package edu.emailman.blackjack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.emailman.blackjack.ui.theme.BlackjackColors

data class ChipValue(val amount: Int, val color: Color)

val standardChips = listOf(
    ChipValue(10, BlackjackColors.ChipWhite),
    ChipValue(25, BlackjackColors.ChipRed),
    ChipValue(50, BlackjackColors.ChipBlue),
    ChipValue(100, BlackjackColors.ChipGreen),
    ChipValue(500, BlackjackColors.ChipBlack)
)

@Composable
fun ChipSelector(
    selectedAmount: Int,
    playerChips: Int,
    onChipSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        standardChips.forEach { chip ->
            ChipComposable(
                value = chip.amount,
                color = chip.color,
                isSelected = selectedAmount == chip.amount,
                isEnabled = chip.amount <= playerChips,
                onClick = { onChipSelected(chip.amount) }
            )
        }
    }
}

@Composable
fun ChipComposable(
    value: Int,
    color: Color,
    isSelected: Boolean,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) BlackjackColors.Gold else Color.White.copy(alpha = 0.5f)
    val alpha = if (isEnabled) 1f else 0.4f

    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(color.copy(alpha = alpha))
            .border(
                width = if (isSelected) 4.dp else 2.dp,
                color = borderColor.copy(alpha = alpha),
                shape = CircleShape
            )
            .clickable(enabled = isEnabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$value",
            color = if (color == BlackjackColors.ChipWhite) Color.Black else Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
