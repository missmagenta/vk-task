package com.example.task.client.jsonplaceholder.dto.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateUserResponse(
        @JsonProperty("id") long id,
        @JsonProperty("name") String name,
        @JsonProperty("username") String username,
        @JsonProperty("email") String email,
        @JsonProperty("address") UserAddress address,
        @JsonProperty("phone") String phone,
        @JsonProperty("website") String website,
        @JsonProperty("company") UserCompany company
) {
}
