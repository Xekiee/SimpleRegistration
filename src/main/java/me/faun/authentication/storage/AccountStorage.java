package me.faun.authentication.storage;

import me.faun.authentication.models.Account;
import java.util.Optional;

public interface AccountStorage {
    void save(Account account);
    Optional<Account> findByUsername(String username);
    boolean exists(String username);
}