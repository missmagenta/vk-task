package com.example.task.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserAuthRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
