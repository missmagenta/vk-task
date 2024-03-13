package com.example.task.client.jsonplaceholder.dto.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(
        @JsonProperty("id") @NotNull long id,
        @JsonProperty("name") @NotNull String name,
        @JsonProperty("username") @NotNull String username,
        @JsonProperty("email") @NotNull String email,
        @JsonProperty("address") @NotNull UserAddress address,
        @JsonProperty("phone") @NotNull String phone,
        @JsonProperty("website") @NotNull String website,
        @JsonProperty("company") @NotNull UserCompany company
) {
}
