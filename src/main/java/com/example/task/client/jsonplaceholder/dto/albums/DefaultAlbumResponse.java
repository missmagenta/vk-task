package com.example.task.client.jsonplaceholder.dto.albums;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DefaultAlbumResponse(
        @JsonProperty("userId") long userId,
        @JsonProperty("id") long id,
        @JsonProperty("title") String title
) {
}
