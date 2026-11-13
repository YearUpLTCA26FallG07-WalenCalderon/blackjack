package blackjackgame;

import blackjack.model.Card;
import blackjack.model.Deck;
import blackjack.model.Hand;
import blackjack.model.Player;
import blackjack.ui.UI;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    private static final int STARTING_CARDS = 2;

    private final Deck deck;
    private final UI ui;

    public BlackjackGame(Deck deck, UI ui) {
        this.deck = deck;
        this.ui = ui;
    }

    public void playRound() {
        int count = ui.askPlayerCount(deck.getSize() / STARTING_CARDS);
        List<Player> players = new ArrayList<>();
        for (int number = 1; number <= count; number++) {
            players.add(new Player(ui.askPlayerName(number)));
        }

        deck.shuffle();
        for (int card = 0; card < STARTING_CARDS; card++) {
            for (Player player : players) {
                dealCard(player);
            }
        }

        ui.showHands("Starting hands", players);
        for (Player player : players) {
            playTurn(player);
            if (deck.getSize() == 0) {
                ui.showDeckExhausted();
                break;
            }
        }
        ui.showHands("Final hands", players);
        announceResult(players);
    }

    private void playTurn(Player player) {
        while (deck.getSize() > 0 && player.getHand().getValue() < Hand.BLACKJACK) {
            if (!ui.wantsHit(player)) {
                return;
            }
            dealCard(player);
            ui.showHand(player);
        }
    }

    private void dealCard(Player player) {
        Card card = deck.deal();
        card.flip();
        player.getHand().deal(card);
    }

    private void announceResult(List<Player> players) {
        int winningScore = 0;
        List<Player> winners = new ArrayList<>();
        for (Player player : players) {
            int score = player.getHand().getValue();
            if (score > Hand.BLACKJACK || score < winningScore) {
                continue;
            }
            if (score > winningScore) {
                winningScore = score;
                winners.clear();
            }
            winners.add(player);
        }
        ui.showResult(winners, winningScore);
    }
}