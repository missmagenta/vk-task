package com.example.task.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


public record UserAuthRequest(@JsonProperty("username") @NotBlank String username,
                              @NotBlank String password) {

}
