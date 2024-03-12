package com.example.task.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Getter
public class ApplicationConfig {
//    private String POSTS_API_BASE_URL = "https://jsonplaceholder.typicode.com/posts";
//    private String USERS_API_BASE_URL = "https://jsonplaceholder.typicode.com/users";
//    private String ALBUMS_API_BASE_URL = "https://jsonplaceholder.typicode.com/albums";
    public static final String API_BASE_URL = "https://jsonplaceholder.typicode.com";
}
