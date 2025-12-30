package edu.emailman.blackjack.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import edu.emailman.blackjack.model.Card

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
            Image(
                painter = getCardBackPainter(),
                contentDescription = "Card back",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Fit
            )
        }
    }
}
