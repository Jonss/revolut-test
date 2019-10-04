package domain.models;

public enum Currency {
    GBP("Pound Sterling", "£"),
    BRL("Brazilian Real", "R$");

    private final String fullName;
    private final String symbol;

    Currency(String name, String symbol) {
        this.fullName = name;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getFullName() {
        return fullName;
    }
}
