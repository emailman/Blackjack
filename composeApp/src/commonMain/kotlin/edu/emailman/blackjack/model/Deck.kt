package edu.emailman.blackjack.model

import kotlin.random.Random

class Deck(private val random: Random = Random.Default) {
    private val cards: MutableList<Card> = mutableListOf()

    val remainingCards: Int get() = cards.size
    val needsReshuffle: Boolean get() = cards.size < RESHUFFLE_THRESHOLD

    init {
        reset()
    }

    fun reset() {
        cards.clear()
        Suit.entries.forEach { suit ->
            Rank.entries.forEach { rank ->
                cards.add(Card(rank, suit))
            }
        }
        shuffle()
    }

    fun shuffle() {
        cards.shuffle(random)
    }

    fun draw(faceUp: Boolean = true): Card? {
        return cards.removeLastOrNull()?.copy(isFaceUp = faceUp)
    }

    companion object {
        const val RESHUFFLE_THRESHOLD = 15
    }
}
