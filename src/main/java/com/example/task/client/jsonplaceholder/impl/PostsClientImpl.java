package com.example.task.client.jsonplaceholder.impl;

import com.example.task.client.jsonplaceholder.PostsClient;
import com.example.task.client.jsonplaceholder.dto.posts.AddPostRequest;
import com.example.task.client.jsonplaceholder.dto.posts.PostResponse;
import com.example.task.client.jsonplaceholder.dto.posts.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class PostsClientImpl implements PostsClient {

    private final PostsRepositoryService service;

    @Override
    @CachePut(cacheNames = "posts", key = "#id")
    public PostResponse getPost(long id) {
        return service.getPost(id);
    }

    @Override
    public PostResponse[] listPosts() {
        return service.listPosts();
    }

    @Override
    public ResponseEntity<PostResponse> addPost(String title, String body, long userId) {
        return service.addPost(new AddPostRequest(title, body, userId));
    }

    @Override
    public ResponseEntity<PostResponse> updatePost(long id, String title, String body, long userId) {
        return service.updatePost(id, new UpdatePostRequest(id, title, body, userId));
    }

    @Override
    public Void deletePost(long id) {
        return service.deletePost(id);
    }
}
