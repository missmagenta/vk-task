package com.example.task.client.jsonplaceholder.dto.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserCompany(
        @JsonProperty("name") String name,
        @JsonProperty("catchPhrase") String catchPhrase,
        @JsonProperty("bs") String bs
) {
}
