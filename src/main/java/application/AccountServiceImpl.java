package application;

import domain.models.Account;
import infrastructure.repositories.AccountRepository;

public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
}
