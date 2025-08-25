package me.faun.authentication.storage;

import com.google.inject.Singleton;
import me.faun.authentication.models.Account;
import org.apache.commons.csv.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@Singleton
public class CsvAccountStorage implements AccountStorage {
    private static final Path CSV_PATH = Paths.get("accounts.csv");
    private static final String[] HEADERS = {"id", "username", "email", "passwordHash", "createdAt", "updatedAt"};

    public CsvAccountStorage() {
        createFileIfNotExists();
    }

    private void createFileIfNotExists() {
        try {
            if (!Files.exists(CSV_PATH)) {
                try (CSVPrinter printer = new CSVPrinter(Files.newBufferedWriter(CSV_PATH), CSVFormat.DEFAULT.withHeader(HEADERS))) {
                    System.out.println("Created new accounts.csv file");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize storage", e);
        }
    }

    @Override
    public void save(Account account) {
        List<Account> accounts = readAll();
        accounts.removeIf(a -> a.username().equals(account.username()));
        accounts.add(account);
        writeAll(accounts);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return readAll().stream()
                .filter(account -> account.username().equals(username))
                .findFirst();
    }

    @Override
    public boolean exists(String username) {
        return findByUsername(username).isPresent();
    }

    private List<Account> readAll() {
        try (Reader reader = Files.newBufferedReader(CSV_PATH)) {
            CSVParser parser = CSVFormat.DEFAULT.withHeader(HEADERS).withSkipHeaderRecord().parse(reader);
            List<Account> accounts = new ArrayList<>();
            for (CSVRecord record : parser) {
                accounts.add(new Account(
                        record.get("id"),
                        record.get("username"),
                        record.get("email"),
                        record.get("passwordHash"),
                        record.get("createdAt"),
                        record.get("updatedAt")
                ));
            }
            return accounts;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read accounts", e);
        }
    }

    private void writeAll(List<Account> accounts) {
        try (CSVPrinter printer = new CSVPrinter(Files.newBufferedWriter(CSV_PATH), CSVFormat.DEFAULT.withHeader(HEADERS))) {
            for (Account account : accounts) {
                printer.printRecord(
                        account.id(),
                        account.username(),
                        account.email(),
                        account.passwordHash(),
                        account.createdAt(),
                        account.updatedAt()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write accounts", e);
        }
    }
}