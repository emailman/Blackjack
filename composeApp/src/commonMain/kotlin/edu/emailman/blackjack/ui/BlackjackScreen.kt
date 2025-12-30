package edu.emailman.blackjack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.emailman.blackjack.exitApplication
import edu.emailman.blackjack.model.GamePhase
import edu.emailman.blackjack.ui.components.ActionButtons
import edu.emailman.blackjack.ui.components.BettingControls
import edu.emailman.blackjack.ui.components.HandDisplay
import edu.emailman.blackjack.ui.components.ResultOverlay
import edu.emailman.blackjack.ui.components.StatisticsPanel
import edu.emailman.blackjack.ui.theme.BlackjackColors
import edu.emailman.blackjack.viewmodel.BlackjackViewModel

@Composable
fun BlackjackScreen(
    viewModel: BlackjackViewModel = viewModel { BlackjackViewModel() }
) {
    val gameState by viewModel.gameState.collectAsState()
    val statistics by viewModel.statistics.collectAsState()
    val selectedBet by viewModel.selectedBet.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackjackColors.FeltGreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StatisticsPanel(
                statistics = statistics,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (gameState.phase != GamePhase.BETTING) {
                HandDisplay(
                    hand = gameState.dealerHand,
                    label = "Dealer",
                    showValue = true
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (gameState.phase != GamePhase.BETTING) {
                HandDisplay(
                    hand = gameState.playerHand,
                    label = "Your Hand",
                    showValue = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                if (gameState.phase == GamePhase.PLAYER_TURN) {
                    ActionButtons(
                        canHit = !gameState.isAnimating,
                        canStand = !gameState.isAnimating,
                        onHit = { viewModel.hit() },
                        onStand = { viewModel.stand() }
                    )
                }
            }

            if (gameState.phase == GamePhase.BETTING) {
                Spacer(modifier = Modifier.weight(1f))

                BettingControls(
                    currentBet = selectedBet,
                    playerChips = gameState.playerChips,
                    onBetChange = { viewModel.selectBet(it) },
                    onDeal = { viewModel.placeBet() },
                    onQuit = { exitApplication() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        ResultOverlay(
            result = gameState.result,
            message = gameState.message,
            onNewRound = { viewModel.newRound() },
            onQuit = { exitApplication() },
            visible = gameState.phase == GamePhase.RESULT,
            modifier = Modifier.fillMaxSize()
        )
    }
}
