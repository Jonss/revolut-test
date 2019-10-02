package domain.models;

import java.time.LocalDateTime;

public class Balance {
    private Long total;
    private LocalDateTime lastDeposit;
    private Account account;
    private Currency currency;

    public Balance() {
    }

    public Balance(Long total, LocalDateTime lastDeposit, Account account, Currency currency) {
        this.total = total;
        this.lastDeposit = lastDeposit;
        this.account = account;
        this.currency = currency;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
