package com.example.task.utils;

import com.example.task.client.jsonplaceholder.dto.posts.DefaultPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostsResponseHandler {

    public String handlePost(DefaultPostResponse response) {
        if (response != null) {
            return String.format("Post with id %s. User id - %s. The title: %s. Contents: %s",
                    response.id(), response.userId(), response.title(), response.body());
        } else {
            return "No posts found on given id";
        }
    }

    public List<String> handlePosts(DefaultPostResponse[] responses) {
        return Arrays.stream(responses)
                .toList()
                .stream()
                .map(this::handlePost)
                .toList();
    }
}
