package com.example.task.client.jsonplaceholder.dto.albums;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record AddAlbumRequest(
        @JsonProperty("userId") @NotNull long userId,
        @JsonProperty("title") @NotNull String title
) {
}
