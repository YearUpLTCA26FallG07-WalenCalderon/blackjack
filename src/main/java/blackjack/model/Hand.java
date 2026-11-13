package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int BLACKJACK = 21;
    private final List<Card> cards = new ArrayList<>();

    public void deal(Card card) {
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int getValue() {
        int value = 0;
        int aces = 0;
        for (Card card : cards) {
            Rank rank = card.getRank();
            value += rank.getBaseValue();
            if (rank == Rank.ACE) {
                aces++;
            }
        }

        while (value > BLACKJACK && aces > 0) {
            value -= Rank.ACE.getBaseValue() - 1;
            aces--;
        }
        return value;
    }
}