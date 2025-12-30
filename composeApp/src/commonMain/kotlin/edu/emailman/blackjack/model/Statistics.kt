package edu.emailman.blackjack.model

data class Statistics(
    val handsPlayed: Int = 0,
    val wins: Int = 0,
    val losses: Int = 0,
    val pushes: Int = 0,
    val blackjacks: Int = 0,
    val totalWagered: Int = 0,
    val totalWon: Int = 0
) {
    val winRate: Float
        get() = if (handsPlayed > 0) wins.toFloat() / handsPlayed else 0f

    val netProfit: Int
        get() = totalWon - totalWagered
}
