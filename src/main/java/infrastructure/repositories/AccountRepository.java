package infrastructure.repositories;

import domain.exceptions.AccountNotCreatedException;
import domain.exceptions.EntityNotFoundException;
import domain.models.Account;
import org.jdbi.v3.core.Jdbi;

import java.util.Optional;

public class AccountRepository {

    private Jdbi jdbi;

    public AccountRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public Account save(Account account) {
        String query = "INSERT INTO accounts" +
                "(email, full_name, nick_name, phone_number, external_id, created_at)" +
                "VALUES (:email, :fullName, :nickName, :phoneNumber, :externalId, :createdAt);";
        try {
            jdbi.useHandle(handle ->
                handle.createUpdate(query)
                        .bind("email", account.getEmail())
                        .bind("fullName", account.getFullName())
                        .bind("nickName", account.getNickName())
                        .bind("phoneNumber", account.getPhoneNumber())
                        .bind("externalId", account.getExternalId().toString())
                        .bind("createdAt", account.getCreatedAt())
                        .execute()
            );
        } catch (Exception e) {
            throw new AccountNotCreatedException();
        }
        return account;
    }

    public Optional<Account> findAccountByEmail(String email) {
        String query = "SELECT id, email, full_name, " +
                "nick_name, phone_number, " +
                "external_id, created_at " +
                "FROM accounts " +
                "WHERE email = :email;";

        try {
            Optional<Account> optionalAccount = jdbi.withHandle(handle ->
                    handle.createQuery(query).bind("email", email).mapToBean(Account.class).findFirst()
            );

            if(!optionalAccount.isPresent()) {
                throw new EntityNotFoundException("Account not found.");
            }

            return optionalAccount;
        } catch (Exception e) {
            throw new EntityNotFoundException("Account not found.");
        }
    }

    public Optional<Account> findAccountByExternalId(String externalId) {
        String query = "SELECT id, email, full_name, " +
                "nick_name, phone_number, " +
                "external_id, created_at " +
                "FROM accounts " +
                "WHERE external_id = :externalId";

        try {
            Optional<Account> optionalAccount = jdbi.withHandle(handle ->
                    handle.createQuery(query).bind("externalId", externalId).mapToBean(Account.class).findFirst()
            );

            if(!optionalAccount.isPresent()) {
                throw new EntityNotFoundException("Account not found.");
            }

            return optionalAccount;
        } catch (Exception e) {
            throw new EntityNotFoundException("Account not found.");
        }
    }
}
