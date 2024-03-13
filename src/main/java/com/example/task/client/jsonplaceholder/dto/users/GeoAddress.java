package com.example.task.client.jsonplaceholder.dto.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GeoAddress(
        @JsonProperty("lat") String latitude,
        @JsonProperty("lng") String longitude
) {
}
