package edu.emailman.blackjack.model

data class Hand(
    val cards: List<Card> = emptyList()
) {
    val hardValue: Int
        get() = cards.sumOf {
            if (it.rank == Rank.ACE) 1 else it.rank.value
        }

    val softValue: Int
        get() {
            var total = 0
            var aces = 0
            cards.forEach { card ->
                if (card.rank == Rank.ACE) {
                    aces++
                    total += 11
                } else {
                    total += card.rank.value
                }
            }
            while (total > 21 && aces > 0) {
                total -= 10
                aces--
            }
            return total
        }

    val bestValue: Int get() = softValue

    val isBusted: Boolean get() = bestValue > 21

    val isBlackjack: Boolean get() = cards.size == 2 && bestValue == 21

    val isSoft: Boolean get() = softValue != hardValue && softValue <= 21

    val visibleValue: Int
        get() {
            val visibleCards = cards.filter { it.isFaceUp }
            return Hand(visibleCards).bestValue
        }

    fun addCard(card: Card): Hand = copy(cards = cards + card)

    fun revealAll(): Hand = copy(cards = cards.map { it.copy(isFaceUp = true) })
}
