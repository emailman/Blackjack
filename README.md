# Blackjack

A casino-style Blackjack card game built with Kotlin Multiplatform and Compose Multiplatform. Play against the dealer with chip-based betting and track your session statistics.

## Features

- **Single deck gameplay** - Classic blackjack with a standard 52-card deck
- **Dealer hits on soft 17** - Authentic casino rules
- **Chip-based betting** - Start with $1,000 bankroll, bet in increments of $10, $25, $50, $100, or $500
- **Session statistics** - Track wins, losses, pushes, blackjacks, and win rate
- **Card animations** - Smooth dealing and flipping animations
- **Cross-platform** - Runs on Android, Desktop (JVM), and Web (WASM)

## Platforms

| Platform | Status |
|----------|--------|
| Android  | Supported |
| Desktop (JVM) | Supported |
| Web (WASM) | Supported |

## Screenshots

The game features a casino-style green felt table with:
- Dealer's hand at the top
- Player's hand at the bottom
- Betting controls and chip selector
- Hit/Stand action buttons during gameplay
- Result overlay showing win/loss/push outcomes

## How to Play

1. **Place your bet** - Select chip amounts and click DEAL
2. **Play your hand** - Choose HIT to draw cards or STAND to keep your hand
3. **Beat the dealer** - Get closer to 21 than the dealer without going over
4. **Collect winnings** - Blackjack pays 3:2, regular wins pay 1:1

## Game Rules

- **Blackjack** - Ace + 10-value card on first two cards (pays 3:2)
- **Bust** - Going over 21 loses immediately
- **Push** - Tie with dealer, bet is returned
- **Dealer rules** - Must hit on 16 or less, must hit on soft 17

## Project Structure

```
composeApp/src/commonMain/kotlin/edu/emailman/blackjack/
├── model/
│   ├── Card.kt          # Card, Suit, Rank enums
│   ├── Deck.kt          # Deck management
│   ├── Hand.kt          # Hand value calculation
│   ├── GameState.kt     # Game phases and state
│   └── Statistics.kt    # Session stats tracking
├── game/
│   ├── HandEvaluator.kt # Hand comparison, payouts
│   ├── DealerAI.kt      # Dealer hit logic
│   └── BlackjackGame.kt # Core game orchestration
├── viewmodel/
│   └── BlackjackViewModel.kt
├── ui/
│   ├── BlackjackScreen.kt
│   ├── components/
│   │   ├── CardComposable.kt
│   │   ├── CardResources.kt
│   │   ├── HandDisplay.kt
│   │   ├── ChipSelector.kt
│   │   ├── BettingControls.kt
│   │   ├── ActionButtons.kt
│   │   ├── ResultOverlay.kt
│   │   └── StatisticsPanel.kt
│   └── theme/
│       ├── BlackjackColors.kt
│       └── BlackjackTheme.kt
└── App.kt
```

## Build and Run

### Android

```shell
./gradlew :composeApp:installDebug
```

### Desktop (JVM)

```shell
./gradlew :composeApp:run
```

### Web (WASM)

```shell
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

Then open http://localhost:8080 in your browser.

## Technologies

- **Kotlin Multiplatform** - Share code across platforms
- **Compose Multiplatform** - Declarative UI framework
- **Material 3** - Modern design system
- **Kotlin Coroutines** - Asynchronous programming
- **StateFlow** - Reactive state management

## Card Assets

Playing card images are from [hayeah/playing-cards-assets](https://github.com/hayeah/playing-cards-assets), licensed under MIT. Original vector cards by Chris Aguilar and Byron Knoll (public domain).

## License

This project is open source and available under the MIT License.
