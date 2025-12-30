package edu.emailman.blackjack.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edu.emailman.blackjack.model.Suit
import edu.emailman.blackjack.ui.theme.BlackjackColors

@Composable
fun SuitIcon(
    suit: Suit,
    size: Dp = 24.dp,
    modifier: Modifier = Modifier
) {
    val color = if (suit.color == edu.emailman.blackjack.model.CardColor.RED)
        BlackjackColors.CardRed else BlackjackColors.CardBlack

    Canvas(modifier = modifier.size(size)) {
        when (suit) {
            Suit.HEARTS -> drawHeart(color)
            Suit.DIAMONDS -> drawDiamond(color)
            Suit.CLUBS -> drawClub(color)
            Suit.SPADES -> drawSpade(color)
        }
    }
}

private fun DrawScope.drawHeart(color: Color) {
    val width = size.width
    val height = size.height

    val path = Path().apply {
        // Start at bottom point
        moveTo(width * 0.5f, height * 0.9f)
        // Left curve
        cubicTo(
            width * 0.1f, height * 0.6f,
            width * 0.0f, height * 0.3f,
            width * 0.25f, height * 0.15f
        )
        // Top left bump
        cubicTo(
            width * 0.4f, height * 0.0f,
            width * 0.5f, height * 0.1f,
            width * 0.5f, height * 0.25f
        )
        // Top right bump
        cubicTo(
            width * 0.5f, height * 0.1f,
            width * 0.6f, height * 0.0f,
            width * 0.75f, height * 0.15f
        )
        // Right curve
        cubicTo(
            width * 1.0f, height * 0.3f,
            width * 0.9f, height * 0.6f,
            width * 0.5f, height * 0.9f
        )
        close()
    }
    drawPath(path, color, style = Fill)
}

private fun DrawScope.drawDiamond(color: Color) {
    val width = size.width
    val height = size.height

    val path = Path().apply {
        moveTo(width * 0.5f, height * 0.05f)  // Top
        lineTo(width * 0.95f, height * 0.5f)   // Right
        lineTo(width * 0.5f, height * 0.95f)   // Bottom
        lineTo(width * 0.05f, height * 0.5f)   // Left
        close()
    }
    drawPath(path, color, style = Fill)
}

private fun DrawScope.drawClub(color: Color) {
    val width = size.width
    val height = size.height
    val radius = width * 0.22f

    // Top circle
    drawCircle(color, radius, Offset(width * 0.5f, height * 0.25f))
    // Bottom left circle
    drawCircle(color, radius, Offset(width * 0.28f, height * 0.52f))
    // Bottom right circle
    drawCircle(color, radius, Offset(width * 0.72f, height * 0.52f))

    // Stem
    val stemPath = Path().apply {
        moveTo(width * 0.35f, height * 0.55f)
        lineTo(width * 0.3f, height * 0.95f)
        lineTo(width * 0.7f, height * 0.95f)
        lineTo(width * 0.65f, height * 0.55f)
        close()
    }
    drawPath(stemPath, color, style = Fill)
}

private fun DrawScope.drawSpade(color: Color) {
    val width = size.width
    val height = size.height

    // Spade body (inverted heart-like shape)
    val path = Path().apply {
        // Start at top point
        moveTo(width * 0.5f, height * 0.05f)
        // Left curve
        cubicTo(
            width * 0.1f, height * 0.35f,
            width * 0.0f, height * 0.55f,
            width * 0.2f, height * 0.7f
        )
        // Bottom left curve
        cubicTo(
            width * 0.35f, height * 0.75f,
            width * 0.4f, height * 0.65f,
            width * 0.5f, height * 0.6f
        )
        // Bottom right curve
        cubicTo(
            width * 0.6f, height * 0.65f,
            width * 0.65f, height * 0.75f,
            width * 0.8f, height * 0.7f
        )
        // Right curve
        cubicTo(
            width * 1.0f, height * 0.55f,
            width * 0.9f, height * 0.35f,
            width * 0.5f, height * 0.05f
        )
        close()
    }
    drawPath(path, color, style = Fill)

    // Stem
    val stemPath = Path().apply {
        moveTo(width * 0.35f, height * 0.6f)
        lineTo(width * 0.3f, height * 0.95f)
        lineTo(width * 0.7f, height * 0.95f)
        lineTo(width * 0.65f, height * 0.6f)
        close()
    }
    drawPath(stemPath, color, style = Fill)
}
