package com.example.task.model.user;

public record LoginedUser(
        String username,
        UserRole role,
        String jwtToken
) {
}