package com.example.task.client.jsonplaceholder.dto.albums;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record UpdateAlbumRequest(
        @JsonProperty("userId") @NotNull long userId,
        @JsonProperty("id") @NotNull long id,
        @JsonProperty("title") @NotNull String title
) {
}
