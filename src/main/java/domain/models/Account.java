package domain.models;

import api.resources.dtos.response.AccountResponseBody;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class Account {
    private Long id;
    private String email;
    private String fullName;
    private String nickName;
    private String phoneNumber;
    private UUID externalId = UUID.randomUUID();
    private LocalDateTime createdAt = LocalDateTime.now();

    public Account(String email, String fullName, String nickName, String phoneNumber) {
        this.email = email;
        this.fullName = fullName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @NotNull
    public AccountResponseBody toAccountResponse() {
        return new AccountResponseBody(this.email, this.fullName, this.externalId);
    }
}
