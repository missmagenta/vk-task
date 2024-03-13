package com.example.task.auth.dto;

import com.example.task.model.user.UserRole;

public record UserPayload(
        String username,
        UserRole role,
        String jwt,
        Integer managerId,
        Integer personId,
        String personName,
        String email
) {
}
