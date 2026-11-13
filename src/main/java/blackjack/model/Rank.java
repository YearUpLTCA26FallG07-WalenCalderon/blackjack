package blackjack.model;

public enum Rank {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ACE("A", 11);

    private final String symbol;
    private final int baseValue;

    Rank(String symbol, int baseValue) {
        this.symbol = symbol;
        this.baseValue = baseValue;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getBaseValue() {
        return baseValue;
    }

    static Rank fromSymbol(String symbol) {
        String trimmedSymbol = symbol.trim();
        for (Rank rank : values()) {
            if (rank.symbol.equals(trimmedSymbol)) {
                return rank;
            }
        }
        throw new IllegalArgumentException("Unknown card rank: " + symbol);
    }
}