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

    public String getSymbol() {
        return this.name;
    }

    public String getFullName() {
        return name;
    }
}
