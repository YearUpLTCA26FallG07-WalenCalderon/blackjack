package blackjack.ui;

import blackjack.model.Card;
import blackjack.model.Hand;
import blackjack.model.Player;

import java.io.PrintStream;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringJoiner;

public class UI {
    private final Scanner input;
    private final PrintStream output;

    public UI(Scanner input, PrintStream output) {
        this.input = input;
        this.output = output;
    }

    public int askPlayerCount(int maxPlayers) {
        while (true) {
            String answer = prompt("How many players (1-" + maxPlayers + ")? ");
            try {
                int count = Integer.parseInt(answer);
                if (count >= 1 && count <= maxPlayers) {
                    return count;
                }
            } catch (NumberFormatException ignored) {
                // Use the same message for non-numeric and out-of-range answers.
            }
            output.println("Enter a whole number from 1 to " + maxPlayers + ".");
        }
    }

    public String askPlayerName(int playerNumber) {
        while (true) {
            String name = prompt("Name for player " + playerNumber + ": ");
            if (!name.isBlank()) {
                return name;
            }
            output.println("Enter a name.");
        }
    }

    public boolean wantsHit(Player player) {
        while (true) {
            String answer = prompt(player.getName() + ", Hit or Stay? ").toLowerCase(Locale.ROOT);
            switch (answer) {
                case "hit", "h":
                    return true;
                case "stay", "s":
                    return false;
                default:
                    output.println("Enter Hit or Stay.");
            }
        }
    }

    public void showHands(String heading, List<Player> players) {
        output.println();
        output.println(heading);
        for (Player player : players) {
            showHand(player);
        }
    }

    public void showHand(Player player) {
        Hand hand = player.getHand();
        StringJoiner cards = new StringJoiner(", ");
        for (Card card : hand.getCards()) {
            cards.add(card.getValue() + " of " + card.getSuit());
        }

        int value = hand.getValue();
        String status = value > Hand.BLACKJACK ? " (bust)" : "";
        output.println(player.getName() + ": " + cards + " | " + value + status);
    }

    public void showDeckExhausted() {
        output.println("The deck is empty. No more cards can be dealt.");
    }

    public void showResult(List<Player> winners, int winningScore) {
        if (winners.isEmpty()) {
            output.println("No winner. All players went over 21.");
        } else if (winners.size() == 1) {
            output.println("Winner: " + winners.get(0).getName() + " with " + winningScore + ".");
        } else {
            StringJoiner names = new StringJoiner(", ");
            for (Player winner : winners) {
                names.add(winner.getName());
            }
            output.println("Tie at " + winningScore + " between " + names + ".");
        }
    }

    public void showInputClosed() {
        output.println();
        output.println("Input ended. The round was canceled.");
    }

    private String prompt(String message) {
        output.print(message);
        output.flush();
        return input.nextLine().strip();
    }
}