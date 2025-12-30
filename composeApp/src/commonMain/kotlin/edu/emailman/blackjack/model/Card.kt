package edu.emailman.blackjack.model

enum class CardColor { RED, BLACK }

enum class Suit(val symbol: String, val color: CardColor, val resourceName: String) {
    HEARTS("\u2665", CardColor.RED, "hearts"),
    DIAMONDS("\u2666", CardColor.RED, "diamonds"),
    CLUBS("\u2663", CardColor.BLACK, "clubs"),
    SPADES("\u2660", CardColor.BLACK, "spades")
}

enum class Rank(val symbol: String, val value: Int, val resourceName: String) {
    ACE("A", 11, "ace"),
    TWO("2", 2, "2"),
    THREE("3", 3, "3"),
    FOUR("4", 4, "4"),
    FIVE("5", 5, "5"),
    SIX("6", 6, "6"),
    SEVEN("7", 7, "7"),
    EIGHT("8", 8, "8"),
    NINE("9", 9, "9"),
    TEN("10", 10, "10"),
    JACK("J", 10, "jack"),
    QUEEN("Q", 10, "queen"),
    KING("K", 10, "king")
}

data class Card(
    val rank: Rank,
    val suit: Suit,
    val isFaceUp: Boolean = true
) {
    val displayValue: String get() = "${rank.symbol}${suit.symbol}"
    val isAce: Boolean get() = rank == Rank.ACE
}
