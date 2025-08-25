package me.faun.authentication;

import me.faun.authentication.storage.AccountStorage;
import me.faun.authentication.util.ValidationUtils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AuthService {
    private final AccountStorage accountStorage;

    public AuthService(AccountStorage accountStorage) {
        this.accountStorage = accountStorage;
    }

    public boolean login(String username, String password) {
        return accountStorage.findByUsername(username)
                .map(account -> verifyPassword(password, account.passwordHash()))
                .orElse(false);
    }

    public boolean register(String username, String email, String password) {
        if (!ValidationUtils.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!ValidationUtils.isValidPassword(password)) {
            throw new IllegalArgumentException(ValidationUtils.getPasswordRequirements());
        }
        if (accountStorage.exists(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        return true;
    }

    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }

    public boolean verifyPassword(String password, String hash) {
        return hash.equals(hashPassword(password));
    }
}