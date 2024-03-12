package com.example.task.auth.dto;

import com.example.task.model.person.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserSignUpRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    @NotBlank
    @Size(min = 3, max = 20)
    private String surname;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private Gender personGender;
    @NotNull
    private String email;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
