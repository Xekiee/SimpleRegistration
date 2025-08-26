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
    private final AuthService authService = new AuthService(accountStorage);

    @Test
    void loginWithIncorrectCredentials() {
        authService.register("morganMalay", "morganMalay@gmail.com", "morganMalay022!");
        boolean inCorrectCredentials = authService.login("invalidcredential", "invalidcredential");
        assertFalse(inCorrectCredentials);
    }

    @Test
    void loginWithCorrectCredentials() {
        authService.register("validUsername", "morganMalay@gmail.com", "validPassword1!");
        boolean correctCredentials = authService.login("validUsername", "validPassword1!");
        assertTrue(correctCredentials);
    }

    @Test
    void loginWithInvalidUsername() {
        authService.register("validUsername", "morganMalay@gmail.com", "validPassword1!");
        boolean invalidUsername = authService.login(" InvalidUsername", "validPassword1!");
        assertFalse(invalidUsername);
    }

    @Test
    void loginWithInvalidPassword() {
        authService.register("validUsername", "morganMalay@gmail.com", "validPassword1!");
        boolean invalidPassword = authService.login("validUsername", "InvalidPassword");
        assertFalse(invalidPassword);
    }

    @Test
    void registerNewAccount() {
        authService.register("morganMalay", "morganMalay@gmail.com", "$2a$12$testUserHashExample1234567890abcdedd");
        boolean newAccount = authService.login("morganMalay", "$2a$12$testUserHashExample1234567890abcdedd");
        assertTrue(newAccount);
    }
    @Test
    void registeredInvalidEmail(){
        assertThrows(IllegalArgumentException.class, () -> {
            authService.register("morganMalay","morganMalaygmail.com","morganMalay022!");
        });
    }

    @Test
    void loginRegisteredExistingUsername() {
        assertThrows(IllegalArgumentException.class, () -> {
            authService.register("testUser", "morganMalay@gmail.com", "morganMalay022");
        });
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