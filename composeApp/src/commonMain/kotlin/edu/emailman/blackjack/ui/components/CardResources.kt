package edu.emailman.blackjack.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import blackjack.composeapp.generated.resources.Res
import blackjack.composeapp.generated.resources.card_ace_clubs
import blackjack.composeapp.generated.resources.card_ace_diamonds
import blackjack.composeapp.generated.resources.card_ace_hearts
import blackjack.composeapp.generated.resources.card_ace_spades
import blackjack.composeapp.generated.resources.card_2_clubs
import blackjack.composeapp.generated.resources.card_2_diamonds
import blackjack.composeapp.generated.resources.card_2_hearts
import blackjack.composeapp.generated.resources.card_2_spades
import blackjack.composeapp.generated.resources.card_3_clubs
import blackjack.composeapp.generated.resources.card_3_diamonds
import blackjack.composeapp.generated.resources.card_3_hearts
import blackjack.composeapp.generated.resources.card_3_spades
import blackjack.composeapp.generated.resources.card_4_clubs
import blackjack.composeapp.generated.resources.card_4_diamonds
import blackjack.composeapp.generated.resources.card_4_hearts
import blackjack.composeapp.generated.resources.card_4_spades
import blackjack.composeapp.generated.resources.card_5_clubs
import blackjack.composeapp.generated.resources.card_5_diamonds
import blackjack.composeapp.generated.resources.card_5_hearts
import blackjack.composeapp.generated.resources.card_5_spades
import blackjack.composeapp.generated.resources.card_6_clubs
import blackjack.composeapp.generated.resources.card_6_diamonds
import blackjack.composeapp.generated.resources.card_6_hearts
import blackjack.composeapp.generated.resources.card_6_spades
import blackjack.composeapp.generated.resources.card_7_clubs
import blackjack.composeapp.generated.resources.card_7_diamonds
import blackjack.composeapp.generated.resources.card_7_hearts
import blackjack.composeapp.generated.resources.card_7_spades
import blackjack.composeapp.generated.resources.card_8_clubs
import blackjack.composeapp.generated.resources.card_8_diamonds
import blackjack.composeapp.generated.resources.card_8_hearts
import blackjack.composeapp.generated.resources.card_8_spades
import blackjack.composeapp.generated.resources.card_9_clubs
import blackjack.composeapp.generated.resources.card_9_diamonds
import blackjack.composeapp.generated.resources.card_9_hearts
import blackjack.composeapp.generated.resources.card_9_spades
import blackjack.composeapp.generated.resources.card_10_clubs
import blackjack.composeapp.generated.resources.card_10_diamonds
import blackjack.composeapp.generated.resources.card_10_hearts
import blackjack.composeapp.generated.resources.card_10_spades
import blackjack.composeapp.generated.resources.card_jack_clubs
import blackjack.composeapp.generated.resources.card_jack_diamonds
import blackjack.composeapp.generated.resources.card_jack_hearts
import blackjack.composeapp.generated.resources.card_jack_spades
import blackjack.composeapp.generated.resources.card_queen_clubs
import blackjack.composeapp.generated.resources.card_queen_diamonds
import blackjack.composeapp.generated.resources.card_queen_hearts
import blackjack.composeapp.generated.resources.card_queen_spades
import blackjack.composeapp.generated.resources.card_king_clubs
import blackjack.composeapp.generated.resources.card_king_diamonds
import blackjack.composeapp.generated.resources.card_king_hearts
import blackjack.composeapp.generated.resources.card_king_spades
import blackjack.composeapp.generated.resources.card_back
import edu.emailman.blackjack.model.Card
import edu.emailman.blackjack.model.Rank
import edu.emailman.blackjack.model.Suit
import org.jetbrains.compose.resources.painterResource

@Composable
fun getCardPainter(card: Card): Painter {
    return when (card.rank) {
        Rank.ACE -> when (card.suit) {
            Suit.CLUBS -> painterResource(Res.drawable.card_ace_clubs)
            Suit.DIAMONDS -> painterResource(Res.drawable.card_ace_diamonds)
            Suit.HEARTS -> painterResource(Res.drawable.card_ace_hearts)
            Suit.SPADES -> painterResource(Res.drawable.card_ace_spades)
        }
        Rank.TWO -> when (card.suit) {
            Suit.CLUBS -> painterResource(Res.drawable.card_2_clubs)
            Suit.DIAMONDS -> painterResource(Res.drawable.card_2_diamonds)
            Suit.HEARTS -> painterResource(Res.drawable.card_2_hearts)
            Suit.SPADES -> painterResource(Res.drawable.card_2_spades)
        }
        Rank.THREE -> when (card.suit) {
            Suit.CLUBS -> painterResource(Res.drawable.card_3_clubs)
            Suit.DIAMONDS -> painterResource(Res.drawable.card_3_diamonds)
            Suit.HEARTS -> painterResource(Res.drawable.card_3_hearts)
            Suit.SPADES -> painterResource(Res.drawable.card_3_spades)
        }
        Rank.FOUR -> when (card.suit) {
            Suit.CLUBS -> painterResource(Res.drawable.card_4_clubs)
            Suit.DIAMONDS -> painterResource(Res.drawable.card_4_diamonds)
            Suit.HEARTS -> painterResource(Res.drawable.card_4_hearts)
            Suit.SPADES -> painterResource(Res.drawable.card_4_spades)
        }
        Rank.FIVE -> when (card.suit) {
            Suit.CLUBS -> painterResource(Res.drawable.card_5_clubs)
            Suit.DIAMONDS -> painterResource(Res.drawable.card_5_diamonds)
            Suit.HEARTS -> painterResource(Res.drawable.card_5_hearts)
            Suit.SPADES -> painterResource(Res.drawable.card_5_spades)
        }
        Rank.SIX -> when (card.suit) {
            Suit.CLUBS -> painterResource(Res.drawable.card_6_clubs)
            Suit.DIAMONDS -> painterResource(Res.drawable.card_6_diamonds)
            Suit.HEARTS -> painterResource(Res.drawable.card_6_hearts)
            Suit.SPADES -> painterResource(Res.drawable.card_6_spades)
        }
        Rank.SEVEN -> when (card.suit) {
            Suit.CLUBS -> painterResource(Res.drawable.card_7_clubs)
            Suit.DIAMONDS -> painterResource(Res.drawable.card_7_diamonds)
            Suit.HEARTS -> painterResource(Res.drawable.card_7_hearts)
            Suit.SPADES -> painterResource(Res.drawable.card_7_spades)
        }
        Rank.EIGHT -> when (card.suit) {
            Suit.CLUBS -> painterResource(Res.drawable.card_8_clubs)
            Suit.DIAMONDS -> painterResource(Res.drawable.card_8_diamonds)
            Suit.HEARTS -> painterResource(Res.drawable.card_8_hearts)
            Suit.SPADES -> painterResource(Res.drawable.card_8_spades)
        }
        Rank.NINE -> when (card.suit) {
            Suit.CLUBS -> painterResource(Res.drawable.card_9_clubs)
            Suit.DIAMONDS -> painterResource(Res.drawable.card_9_diamonds)
            Suit.HEARTS -> painterResource(Res.drawable.card_9_hearts)
            Suit.SPADES -> painterResource(Res.drawable.card_9_spades)
        }
        Rank.TEN -> when (card.suit) {
            Suit.CLUBS -> painterResource(Res.drawable.card_10_clubs)
            Suit.DIAMONDS -> painterResource(Res.drawable.card_10_diamonds)
            Suit.HEARTS -> painterResource(Res.drawable.card_10_hearts)
            Suit.SPADES -> painterResource(Res.drawable.card_10_spades)
        }
        Rank.JACK -> when (card.suit) {
            Suit.CLUBS -> painterResource(Res.drawable.card_jack_clubs)
            Suit.DIAMONDS -> painterResource(Res.drawable.card_jack_diamonds)
            Suit.HEARTS -> painterResource(Res.drawable.card_jack_hearts)
            Suit.SPADES -> painterResource(Res.drawable.card_jack_spades)
        }
        Rank.QUEEN -> when (card.suit) {
            Suit.CLUBS -> painterResource(Res.drawable.card_queen_clubs)
            Suit.DIAMONDS -> painterResource(Res.drawable.card_queen_diamonds)
            Suit.HEARTS -> painterResource(Res.drawable.card_queen_hearts)
            Suit.SPADES -> painterResource(Res.drawable.card_queen_spades)
        }
        Rank.KING -> when (card.suit) {
            Suit.CLUBS -> painterResource(Res.drawable.card_king_clubs)
            Suit.DIAMONDS -> painterResource(Res.drawable.card_king_diamonds)
            Suit.HEARTS -> painterResource(Res.drawable.card_king_hearts)
            Suit.SPADES -> painterResource(Res.drawable.card_king_spades)
        }
    }
}

@Composable
fun getCardBackPainter(): Painter {
    return painterResource(Res.drawable.card_back)
}
