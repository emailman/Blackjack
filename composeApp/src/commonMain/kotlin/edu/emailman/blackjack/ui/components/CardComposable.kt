package edu.emailman.blackjack.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import edu.emailman.blackjack.model.Card
import edu.emailman.blackjack.ui.theme.BlackjackColors

@Composable
fun CardComposable(
    card: Card?,
    modifier: Modifier = Modifier,
    animateDeal: Boolean = false,
    dealDelay: Int = 0
) {
    val showFront = card?.isFaceUp ?: false

    val rotation by animateFloatAsState(
        targetValue = if (showFront) 0f else 180f,
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
        label = "cardFlip"
    )

    var hasDealt by remember { mutableStateOf(!animateDeal) }
    val offsetX by animateFloatAsState(
        targetValue = if (hasDealt) 0f else 300f,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = dealDelay,
            easing = FastOutSlowInEasing
        ),
        label = "dealOffset"
    )
    val alpha by animateFloatAsState(
        targetValue = if (hasDealt) 1f else 0f,
        animationSpec = tween(durationMillis = 300, delayMillis = dealDelay),
        label = "dealAlpha"
    )

    LaunchedEffect(Unit) {
        if (animateDeal) {
            hasDealt = true
        }
    }

    Box(
        modifier = modifier
            .width(80.dp)
            .height(112.dp)
            .graphicsLayer {
                translationX = offsetX
                this.alpha = alpha
                rotationY = rotation
                cameraDistance = 12f * density
            }
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        if (rotation <= 90f && card != null && card.isFaceUp) {
            Image(
                painter = getCardPainter(card),
                contentDescription = "${card.rank.symbol} of ${card.suit.name}",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Fit
            )
        } else {
            CardBack(modifier = Modifier.matchParentSize())
        }
    }
}

@Composable
fun CardBack(modifier: Modifier = Modifier) {
    val backgroundColor = BlackjackColors.CardBack
    val patternColor = BlackjackColors.CardBackPattern
    val borderColor = BlackjackColors.Gold

    Box(
        modifier = modifier
            .background(backgroundColor)
            .border(
                width = 3.dp,
                color = borderColor,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(3.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // Draw diamond pattern
            val diamondSize = 12f
            val spacingX = diamondSize * 1.5f
            val spacingY = diamondSize * 1.5f

            var rowIndex = 0
            var y = spacingY / 2
            while (y < height) {
                val offsetX = if (rowIndex % 2 == 0) 0f else spacingX / 2
                var x = offsetX + spacingX / 2
                while (x < width) {
                    val path = Path().apply {
                        moveTo(x, y - diamondSize / 2)
                        lineTo(x + diamondSize / 2, y)
                        lineTo(x, y + diamondSize / 2)
                        lineTo(x - diamondSize / 2, y)
                        close()
                    }
                    drawPath(path, patternColor)
                    x += spacingX
                }
                y += spacingY
                rowIndex++
            }

            // Draw center oval decoration
            val centerX = width / 2
            val centerY = height / 2
            val ovalWidth = width * 0.6f
            val ovalHeight = height * 0.4f

            drawOval(
                color = patternColor,
                topLeft = Offset(centerX - ovalWidth / 2, centerY - ovalHeight / 2),
                size = androidx.compose.ui.geometry.Size(ovalWidth, ovalHeight)
            )

            drawOval(
                color = backgroundColor,
                topLeft = Offset(centerX - ovalWidth / 2 + 4, centerY - ovalHeight / 2 + 4),
                size = androidx.compose.ui.geometry.Size(ovalWidth - 8, ovalHeight - 8)
            )

            // Inner decorative diamond in center
            val centerDiamondSize = minOf(ovalWidth, ovalHeight) * 0.4f
            val centerPath = Path().apply {
                moveTo(centerX, centerY - centerDiamondSize / 2)
                lineTo(centerX + centerDiamondSize / 2, centerY)
                lineTo(centerX, centerY + centerDiamondSize / 2)
                lineTo(centerX - centerDiamondSize / 2, centerY)
                close()
            }
            drawPath(centerPath, patternColor)
        }
    }
}
