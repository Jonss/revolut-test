package domain.models;

public enum Currency {
    GBP("Pound Sterling", "£"),
    BRL("Brazilian Real", "R$");

    private final String name;
    private final String symbol;

    Currency(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }
}
