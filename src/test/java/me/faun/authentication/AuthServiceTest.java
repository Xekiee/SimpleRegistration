package me.faun.authentication;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import me.faun.authentication.models.Account;
import me.faun.authentication.storage.AccountStorage;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private final AccountStorage accountStorage = new MockAccountStorage();

    @Test
    void login() {
    }

    @Test
    void register() {
    }

    @Test
    void hashPassword() {
    }

    @Test
    void verifyPassword() {
    }
}

class MockAccountStorage implements AccountStorage {
    private final List<Account> accounts = new java.util.ArrayList<>(Arrays.asList(
            new Account(
                    "unique-id-123",
                    "testUser",
                    "testUser@example.com",
                    "$2a$10$testUserHashExample1234567890abcdefg", // mock hash
                    "2023-10-01T12:00:00Z",
                    "2023-10-01T12:00:00Z"),
            new Account(
                    "unique-id-456",
                    "anotherUser",
                    "anotherUser@example.com",
                    "$2a$10$anotherUserHashExample0987654321abcdefg", // mock hash
                    "2023-10-02T12:00:00Z",
                    "2023-10-02T12:00:00Z")));

    @Override
    public Optional<Account> findByUsername(String username) {
        return accounts.stream().filter(acc -> acc.username().equals(username)).findFirst();
    }

    @Override
    public boolean exists(String username) {
        return accounts.stream().anyMatch(acc -> acc.username().equals(username));
    }

    @Override
    public void save(Account account) {
        accounts.add(account);
    }
}