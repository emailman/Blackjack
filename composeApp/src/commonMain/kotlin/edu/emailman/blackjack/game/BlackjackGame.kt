package edu.emailman.blackjack.game

import edu.emailman.blackjack.model.Deck
import edu.emailman.blackjack.model.GamePhase
import edu.emailman.blackjack.model.GameResult
import edu.emailman.blackjack.model.GameState
import edu.emailman.blackjack.model.Hand

class BlackjackGame {
    private var deck = Deck()

    fun startNewRound(currentState: GameState, betAmount: Int): GameState {
        if (deck.needsReshuffle) {
            deck.reset()
        }

        val playerCard1 = deck.draw(faceUp = true)!!
        val dealerCard1 = deck.draw(faceUp = true)!!
        val playerCard2 = deck.draw(faceUp = true)!!
        val dealerHoleCard = deck.draw(faceUp = false)!!

        val playerHand = Hand(listOf(playerCard1, playerCard2))
        val dealerHand = Hand(listOf(dealerCard1, dealerHoleCard))

        val newState = currentState.copy(
            phase = GamePhase.PLAYER_TURN,
            playerHand = playerHand,
            dealerHand = dealerHand,
            currentBet = betAmount,
            playerChips = currentState.playerChips - betAmount,
            result = GameResult.NONE,
            message = ""
        )

        return if (playerHand.isBlackjack) {
            resolveDealerBlackjackCheck(newState)
        } else {
            newState
        }
    }

    fun playerHit(currentState: GameState): GameState {
        val newCard = deck.draw(faceUp = true) ?: return currentState
        val newHand = currentState.playerHand.addCard(newCard)

        return if (newHand.isBusted) {
            currentState.copy(
                playerHand = newHand,
                phase = GamePhase.RESULT,
                result = GameResult.DEALER_WIN,
                message = "Bust! You lose."
            )
        } else {
            currentState.copy(playerHand = newHand)
        }
    }

    fun playerStand(currentState: GameState): GameState {
        val revealedDealerHand = currentState.dealerHand.revealAll()
        return currentState.copy(
            dealerHand = revealedDealerHand,
            phase = GamePhase.DEALER_TURN
        )
    }

    fun dealerPlay(currentState: GameState): Pair<GameState, Boolean> {
        if (!DealerAI.shouldHit(currentState.dealerHand)) {
            val result = HandEvaluator.compareHands(
                currentState.playerHand,
                currentState.dealerHand
            )
            val payout = HandEvaluator.calculatePayout(currentState.currentBet, result)
            return Pair(
                currentState.copy(
                    phase = GamePhase.RESULT,
                    result = result,
                    playerChips = currentState.playerChips + payout,
                    message = getResultMessage(result)
                ),
                false
            )
        }

        val newCard = deck.draw(faceUp = true) ?: return Pair(currentState, false)
        val newDealerHand = currentState.dealerHand.addCard(newCard)

        return if (newDealerHand.isBusted) {
            val payout = HandEvaluator.calculatePayout(currentState.currentBet, GameResult.PLAYER_WIN)
            Pair(
                currentState.copy(
                    dealerHand = newDealerHand,
                    phase = GamePhase.RESULT,
                    result = GameResult.PLAYER_WIN,
                    playerChips = currentState.playerChips + payout,
                    message = "Dealer busts! You win!"
                ),
                false
            )
        } else {
            Pair(currentState.copy(dealerHand = newDealerHand), true)
        }
    }

    private fun resolveDealerBlackjackCheck(state: GameState): GameState {
        val dealerHand = state.dealerHand.revealAll()
        val result = HandEvaluator.compareHands(state.playerHand, dealerHand)
        val payout = HandEvaluator.calculatePayout(state.currentBet, result)

        return state.copy(
            dealerHand = dealerHand,
            phase = GamePhase.RESULT,
            result = result,
            playerChips = state.playerChips + payout,
            message = getResultMessage(result)
        )
    }

    private fun getResultMessage(result: GameResult): String {
        return when (result) {
            GameResult.PLAYER_BLACKJACK -> "Blackjack! You win!"
            GameResult.PLAYER_WIN -> "You win!"
            GameResult.DEALER_WIN -> "Dealer wins."
            GameResult.PUSH -> "Push - it's a tie."
            GameResult.NONE -> ""
        }
    }

    fun resetForNewRound(currentState: GameState): GameState {
        return currentState.copy(
            phase = GamePhase.BETTING,
            playerHand = Hand(),
            dealerHand = Hand(),
            currentBet = 0,
            result = GameResult.NONE,
            message = "",
            showResultOverlay = false
        )
    }
}
