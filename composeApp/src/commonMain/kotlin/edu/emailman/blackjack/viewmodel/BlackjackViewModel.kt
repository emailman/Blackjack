package edu.emailman.blackjack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.emailman.blackjack.game.BlackjackGame
import edu.emailman.blackjack.game.HandEvaluator
import edu.emailman.blackjack.model.GamePhase
import edu.emailman.blackjack.model.GameResult
import edu.emailman.blackjack.model.GameState
import edu.emailman.blackjack.model.Statistics
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlackjackViewModel : ViewModel() {

    private val game = BlackjackGame()

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private val _statistics = MutableStateFlow(Statistics())
    val statistics: StateFlow<Statistics> = _statistics.asStateFlow()

    private val _selectedBet = MutableStateFlow(GameState.MINIMUM_BET)
    val selectedBet: StateFlow<Int> = _selectedBet.asStateFlow()

    fun selectBet(amount: Int) {
        val currentChips = _gameState.value.playerChips
        val clampedAmount = amount.coerceIn(
            GameState.MINIMUM_BET,
            minOf(GameState.MAXIMUM_BET, currentChips)
        )
        _selectedBet.value = clampedAmount
    }

    fun placeBet() {
        val betAmount = _selectedBet.value
        if (betAmount > _gameState.value.playerChips) return

        viewModelScope.launch {
            _gameState.update { it.copy(isAnimating = true) }

            val newState = game.startNewRound(_gameState.value, betAmount)

            delay(CARD_DEAL_DELAY * 4)

            _gameState.value = newState.copy(isAnimating = false)

            if (newState.phase == GamePhase.RESULT) {
                updateStatistics(newState.result, betAmount)
                showResultAfterDelay()
            }
        }
    }

    fun hit() {
        if (_gameState.value.phase != GamePhase.PLAYER_TURN) return
        if (_gameState.value.isAnimating) return

        viewModelScope.launch {
            _gameState.update { it.copy(isAnimating = true) }
            delay(CARD_DEAL_DELAY)

            val newState = game.playerHit(_gameState.value)
            _gameState.value = newState.copy(isAnimating = false)

            if (newState.phase == GamePhase.RESULT) {
                updateStatistics(newState.result, newState.currentBet)
                showResultAfterDelay()
            }
        }
    }

    fun stand() {
        if (_gameState.value.phase != GamePhase.PLAYER_TURN) return
        if (_gameState.value.isAnimating) return

        viewModelScope.launch {
            _gameState.update { it.copy(isAnimating = true) }

            var currentState = game.playerStand(_gameState.value)
            _gameState.value = currentState
            delay(CARD_FLIP_DELAY)

            var shouldContinue = true
            while (shouldContinue) {
                delay(DEALER_PLAY_DELAY)
                val (newState, continues) = game.dealerPlay(currentState)
                currentState = newState
                _gameState.value = currentState
                shouldContinue = continues
            }

            _gameState.update { it.copy(isAnimating = false) }
            updateStatistics(currentState.result, currentState.currentBet)
            showResultAfterDelay()
        }
    }

    fun newRound() {
        if (_gameState.value.phase != GamePhase.RESULT) return

        _gameState.value = game.resetForNewRound(_gameState.value)
    }

    fun resetGame() {
        _gameState.value = GameState()
        _statistics.value = Statistics()
        _selectedBet.value = GameState.MINIMUM_BET
    }

    private fun updateStatistics(result: GameResult, betAmount: Int) {
        _statistics.update { stats ->
            val payout = HandEvaluator.calculatePayout(betAmount, result)

            stats.copy(
                handsPlayed = stats.handsPlayed + 1,
                wins = stats.wins + if (result == GameResult.PLAYER_WIN ||
                    result == GameResult.PLAYER_BLACKJACK
                ) 1 else 0,
                losses = stats.losses + if (result == GameResult.DEALER_WIN) 1 else 0,
                pushes = stats.pushes + if (result == GameResult.PUSH) 1 else 0,
                blackjacks = stats.blackjacks + if (result == GameResult.PLAYER_BLACKJACK) 1 else 0,
                totalWagered = stats.totalWagered + betAmount,
                totalWon = stats.totalWon + payout
            )
        }
    }

    private fun showResultAfterDelay() {
        viewModelScope.launch {
            delay(RESULT_OVERLAY_DELAY)
            _gameState.update { it.copy(showResultOverlay = true) }
        }
    }

    companion object {
        private const val CARD_DEAL_DELAY = 300L
        private const val CARD_FLIP_DELAY = 500L
        private const val DEALER_PLAY_DELAY = 800L
        private const val RESULT_OVERLAY_DELAY = 2000L
    }
}
