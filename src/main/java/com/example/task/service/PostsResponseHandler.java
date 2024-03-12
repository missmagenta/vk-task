package com.example.task.service;

import com.example.task.client.jsonplaceholder.dto.posts.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostsResponseHandler {
//    private final PostsClient client;

    public String handlePost(PostResponse response) {
        if (response != null) {
            return String.format("Post with id %s. User id - %s. The title: %s. Contents: %s",
                    response.id(), response.userId(), response.title(), response.body());
        } else {
            return "No posts found on given id";
        }
    }

    public List<String> handlePosts(PostResponse[] responses) {
        return Arrays.stream(responses)
                .toList()
                .stream()
                .map(post -> String.format("Post with id %s. User id - %s. The title: %s. Contents: %s \n",
                        post.id(), post.userId(), post.title(), post.body()))
                .toList();
    }


}
