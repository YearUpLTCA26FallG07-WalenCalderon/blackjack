package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};
        for (String suit : suits) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    public int getSize() {
        return cards.size();
    }
}