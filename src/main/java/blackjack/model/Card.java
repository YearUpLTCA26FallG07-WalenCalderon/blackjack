package blackjack.model;

public class Card {
    private final String suit;
    private final Rank rank;
    private boolean faceUp;

    public Card(String suit, String value) {
        this(suit, Rank.fromSymbol(value));
    }

    public Card(String suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return faceUp ? suit : "#";
    }

    public String getValue() {
        return faceUp ? rank.getSymbol() : "#";
    }

    public int getPointValue() {
        return faceUp ? rank.getBaseValue() : 0;
    }

    Rank getRank() {
        return rank;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void flip() {
        faceUp = !faceUp;
    }
}