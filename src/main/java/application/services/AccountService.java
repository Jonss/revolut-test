package application.services;

import domain.models.Account;

import java.util.Optional;

public interface AccountService {

    Account createAccount(Account account);

    Optional<Account> findAccount(String email);
}
