package me.faun.authentication.models;

public record Account(
        String id,
        String username,
        String email,
        String passwordHash,
        String createdAt,
        String updatedAt
) {
}
