package domain.models;

import java.time.LocalDateTime;

public class Balance {
    private Long id;
    private Long total;
    private LocalDateTime lastDeposit;
    private Long accountId;
    private Currency currency;

    public Balance() {
    }

    public Balance(Long total, LocalDateTime lastDeposit, Long accountId, Currency currency) {
        this.total = total;
        this.lastDeposit = lastDeposit;
        this.accountId = accountId;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public LocalDateTime getLastDeposit() {
        return lastDeposit;
    }

    public void setLastDeposit(LocalDateTime lastDeposit) {
        this.lastDeposit = lastDeposit;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "total=" + total +
                ", lastDeposit=" + lastDeposit +
                ", accountId=" + accountId +
                ", currency=" + currency +
                '}';
    }
}
