package com.example.task.model.user;

import com.example.task.model.user.UserRole;

public record LoginedUser(
        String username,
        UserRole role,
        String jwtToken
) {}