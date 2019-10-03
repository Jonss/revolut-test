package application.services;

import domain.models.Account;
import infrastructure.repositories.AccountRepository;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findAccount(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    @Override
    public Optional<Account> findRevolutIssuer() {
        return accountRepository.findAccountByEmail("issuer@revolut.co.uk");
    }

    @Override
    public Optional<Account> findAccountById(String accountId) {
        return accountRepository.findAccountByExternalId(accountId);
    }
}
