package domain.models;

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


    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
