package com.example.task.client.jsonplaceholder.dto.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GeoAddressResponse(
        @JsonProperty("lat") String latitude,
        @JsonProperty("lng") String longitude
) {
}
