package infrastructure.repositories;

import domain.models.Account;

import javax.sql.DataSource;

public class AccountRepository {


    private DataSource ds;

    public AccountRepository(DataSource ds) {
        this.ds = ds;
    }

    public Account save(Account account) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
}
