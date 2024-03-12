package com.example.task.client.jsonplaceholder.dto.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddUserResponse(
        @JsonProperty("id") long id
) {
}
