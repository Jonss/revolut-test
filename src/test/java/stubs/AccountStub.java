package stubs;

import domain.models.Account;

import java.util.Optional;

public class AccountStub {
    public Optional<Account> regularAccount() {
        Account account = new Account(
                "email@email.com",
                "My FullName",
                "full",
                "+55 11 999999999");
        return Optional.of(account);
    }
}
