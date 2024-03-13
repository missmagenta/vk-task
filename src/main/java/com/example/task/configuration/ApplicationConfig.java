package com.example.task.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Getter
public class ApplicationConfig {
    public static final String API_BASE_URL = "https://jsonplaceholder.typicode.com";
}
