package edu.emailman.blackjack.game

import edu.emailman.blackjack.model.GameResult
import edu.emailman.blackjack.model.Hand

object HandEvaluator {

    fun calculateBestValue(hand: Hand): Int = hand.bestValue

    fun isSoft17(hand: Hand): Boolean {
        return hand.bestValue == 17 && hand.isSoft
    }

    fun compareHands(playerHand: Hand, dealerHand: Hand): GameResult {
        val playerValue = playerHand.bestValue
        val dealerValue = dealerHand.bestValue

        return when {
            playerHand.isBlackjack && !dealerHand.isBlackjack -> GameResult.PLAYER_BLACKJACK
            !playerHand.isBlackjack && dealerHand.isBlackjack -> GameResult.DEALER_WIN
            playerHand.isBlackjack && dealerHand.isBlackjack -> GameResult.PUSH
            playerHand.isBusted -> GameResult.DEALER_WIN
            dealerHand.isBusted -> GameResult.PLAYER_WIN
            playerValue > dealerValue -> GameResult.PLAYER_WIN
            playerValue < dealerValue -> GameResult.DEALER_WIN
            else -> GameResult.PUSH
        }
    }

    fun calculatePayout(bet: Int, result: GameResult): Int {
        return when (result) {
            GameResult.PLAYER_BLACKJACK -> (bet * 2.5).toInt()
            GameResult.PLAYER_WIN -> bet * 2
            GameResult.PUSH -> bet
            GameResult.DEALER_WIN -> 0
            GameResult.NONE -> 0
        }
    }
}
