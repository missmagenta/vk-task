package com.example.task.client.jsonplaceholder.dto.posts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record UpdatePostRequest(
        @JsonProperty("id") @NotNull long id,
        @JsonProperty("title") @NotNull String title,
        @JsonProperty("body") @NotNull String body,
        @JsonProperty("userId") @NotNull long userId) {
}
