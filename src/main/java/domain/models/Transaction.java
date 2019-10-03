package domain.models;

import api.resources.dtos.response.CurrencyDTO;
import api.resources.dtos.response.DepositResponseBody;
import api.resources.dtos.response.DepositValueResponse;
import api.resources.dtos.response.SimpleAccountResponse;

import java.time.LocalDateTime;
import java.util.UUID;

import static domain.models.Operation.WITHDRAWAL;

public class Transaction {
    private Long id;
    private Long amount;
    private Account origin;
    private Account destiny;
    private LocalDateTime createdAt = LocalDateTime.now();
    private UUID externalId = UUID.randomUUID();
    private Operation operation;
    private Currency currency;

    public Transaction(Long amount, Account origin, Account destiny,
                       Operation operation, Currency currency) {
        this.amount = operation == WITHDRAWAL ? Math.abs(amount) * -1: Math.abs(amount);
        this.origin = origin;
        this.destiny = destiny;
        this.operation = operation;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Account getOrigin() {
        return origin;
    }

    public void setOrigin(Account origin) {
        this.origin = origin;
    }

    public Account getDestiny() {
        return destiny;
    }

    public void setDestiny(Account destiny) {
        this.destiny = destiny;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

    public Operation getOperation() {
        return operation;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public DepositResponseBody toDepositResponseBody() {
        SimpleAccountResponse origin = new SimpleAccountResponse(this.origin.getFullName(), this.origin.getEmail());
        SimpleAccountResponse destiny = new SimpleAccountResponse(this.destiny.getFullName(), this.destiny.getEmail());
        CurrencyDTO currencyDTO = new CurrencyDTO(this.currency.name(), this.currency.getSymbol(), this.currency.getFullName());
        DepositValueResponse depositValueResponse = new DepositValueResponse(this.amount, currencyDTO);

        return new DepositResponseBody(depositValueResponse, origin, destiny, this.createdAt);
    }
}
