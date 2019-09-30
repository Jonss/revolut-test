package infrastructure.repositories;

import domain.models.Account;
import org.jdbi.v3.core.Jdbi;

import java.util.Optional;

public class AccountRepository {

    private Jdbi jdbi;

    public AccountRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    // TODO - Handle exception properly
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
            e.printStackTrace();
        }
        return account;
    }

    // TODO - Handle exception properly
    public Optional<Account> findAccountByEmail(String email) {
        String query = "SELECT email, full_name, nick_name, phone_number, external_id, created_at " +
                "FROM accounts WHERE email = :email;";

        try {
            return jdbi.withHandle(handle ->
               handle.createQuery(query).bind("email", email).mapToBean(Account.class).findFirst()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
