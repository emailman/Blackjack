package edu.emailman.blackjack.game

import edu.emailman.blackjack.model.Hand

object DealerAI {

    /**
     * Determines if the dealer should hit based on standard casino rules.
     * Dealer hits on soft 17 when hitOnSoft17 is true.
     */
    fun shouldHit(hand: Hand, hitOnSoft17: Boolean = true): Boolean {
        val value = hand.bestValue

        return when {
            value < 17 -> true
            value == 17 && hand.isSoft && hitOnSoft17 -> true
            else -> false
        }
    }
}
