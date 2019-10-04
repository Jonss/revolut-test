package stubs;

import domain.models.Account;

import java.util.Optional;
import java.util.Random;

public class AccountStub {


    public Optional<Account> build() {
        Account account = new Account(
                String.valueOf(new Random().nextInt()),
                "My FullName",
                "full",
                String.valueOf(new Random().nextInt()));
        return Optional.of(account);
    }
}
