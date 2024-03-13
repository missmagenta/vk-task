package com.example.task.ws;

import lombok.Builder;

@Builder
public record Message(String username, String message) {
}