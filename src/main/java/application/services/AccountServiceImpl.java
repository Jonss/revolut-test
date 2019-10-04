package application.services;

import domain.models.Account;
import infrastructure.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        logger.info("Creating new account");
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
