package com.example.task.client.jsonplaceholder.dto.posts;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddPostRequest(
        @JsonProperty("title") String title,
        @JsonProperty("body") String body,
        @JsonProperty("userId") long userId
) {
}
