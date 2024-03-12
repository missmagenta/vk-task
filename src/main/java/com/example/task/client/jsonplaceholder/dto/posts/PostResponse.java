package com.example.task.client.jsonplaceholder.dto.posts;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostResponse(
        @JsonProperty("id") long id,
        @JsonProperty("title") String title,
        @JsonProperty("body") String body,
        @JsonProperty("userId") long userId
) {
}
