package com.example.task.client.jsonplaceholder.dto.albums;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateAlbumResponse(
        @JsonProperty("id") long id
) {
}
