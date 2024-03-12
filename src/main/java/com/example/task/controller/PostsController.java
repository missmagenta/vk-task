package com.example.task.controller;

import com.example.task.client.jsonplaceholder.PostsClient;
import com.example.task.client.jsonplaceholder.dto.posts.AddPostRequest;
import com.example.task.client.jsonplaceholder.dto.posts.PostResponse;
import com.example.task.service.PostsResponseHandler;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
@PreAuthorize("hasRole('POSTS')")
public class PostsController {

    private final PostsClient postsClient;

    public PostsController(PostsClient postsClient) {
        this.postsClient = postsClient;
    }

    @GetMapping("/{id}")
    @CachePut(cacheNames = "posts", key = "#id")
    public ResponseEntity<PostResponse> getPost(@PathVariable("id") long id) {
        PostResponse response = postsClient.getPost(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PostResponse[]> listPosts() {
        PostResponse[] posts = postsClient.listPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<PostResponse> addPost(@Valid @RequestBody AddPostRequest addPostRequest) {
        ResponseEntity<PostResponse> response = postsClient.addPost(addPostRequest.title(), addPostRequest.body(), addPostRequest.userId());
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
