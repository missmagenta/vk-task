package com.example.task.client.jsonplaceholder.dto.albums;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddAlbumRequest(
        @JsonProperty("userId") long userId,
        @JsonProperty("title") String title
) {
}
