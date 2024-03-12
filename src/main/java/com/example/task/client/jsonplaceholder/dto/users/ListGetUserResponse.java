package com.example.task.client.jsonplaceholder.dto.users;

import java.util.List;

public record ListGetUserResponse(List<GetUserResponse> users) {
}
