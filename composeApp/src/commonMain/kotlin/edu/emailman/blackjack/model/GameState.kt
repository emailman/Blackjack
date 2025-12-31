package edu.emailman.blackjack.model

enum class GamePhase {
    BETTING,
    DEALING,
    PLAYER_TURN,
    DEALER_TURN,
    RESULT
}

enum class GameResult {
    PLAYER_BLACKJACK,
    PLAYER_WIN,
    DEALER_WIN,
    PUSH,
    NONE
}

data class GameState(
    val phase: GamePhase = GamePhase.BETTING,
    val playerHand: Hand = Hand(),
    val dealerHand: Hand = Hand(),
    val currentBet: Int = 0,
    val playerChips: Int = DEFAULT_STARTING_CHIPS,
    val result: GameResult = GameResult.NONE,
    val message: String = "",
    val isAnimating: Boolean = false,
    val showResultOverlay: Boolean = false
) {
    companion object {
        const val DEFAULT_STARTING_CHIPS = 1000
        const val MINIMUM_BET = 10
        const val MAXIMUM_BET = 500
    }
}
