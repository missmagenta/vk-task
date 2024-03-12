package com.example.task.service;

import com.example.task.client.jsonplaceholder.UsersClient;
import com.example.task.client.jsonplaceholder.dto.posts.PostResponse;
import com.example.task.client.jsonplaceholder.dto.users.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersHandler {
    private final UsersClient client;

    public String handleUser(long id) {
        GetUserResponse response = client.getUser(id);
        if (response != null) {
            return String.format("User with id %s. User name - %s. The email: %s. Phone: %s",
                    id, response.name(), response.username(), response.email(), response.phone());
        } else {
            return "No posts found on given id";
        }
    }

    public List<String> handleUsers() {
        return Arrays.stream(client.listUsers())
                .toList()
                .stream()
                .map(post -> String.format("User with id %s. User name - %s. The email: %s. Phone: %s",
                        post.id(), post.username(), post.email(), post.phone()))
                .toList();
    }
}
