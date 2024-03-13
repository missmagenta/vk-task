package com.example.task.controller;

import com.example.task.client.jsonplaceholder.PostsClient;
import com.example.task.client.jsonplaceholder.dto.posts.AddPostRequest;
import com.example.task.client.jsonplaceholder.dto.posts.DefaultPostResponse;
import com.example.task.client.jsonplaceholder.dto.posts.UpdatePostRequest;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;


@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostsClient postsClient;

    public PostsController(PostsClient postsClient) {
        this.postsClient = postsClient;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('POSTS_VIEW', 'ADMIN_VIEW')")
    @CachePut(cacheNames = "posts", key = "#id")
    public ResponseEntity<DefaultPostResponse> getPost(@PathVariable("id") long id) {
        DefaultPostResponse response = postsClient.getPost(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('POSTS_VIEW', 'ADMIN_VIEW')")
    public ResponseEntity<DefaultPostResponse[]> listPosts() {
        DefaultPostResponse[] posts = postsClient.listPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('POSTS_CREATE', 'ADMIN_CREATE')")
    public ResponseEntity<DefaultPostResponse> addPost(@Valid @RequestBody AddPostRequest addPostRequest) {
        ResponseEntity<DefaultPostResponse> response = postsClient.addPost(addPostRequest.title(), addPostRequest.body(), addPostRequest.userId());
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PutExchange("/{id}")
    @PreAuthorize("hasAnyRole('POSTS_UPDATE', 'ADMIN_UPDATE')")
    public ResponseEntity<DefaultPostResponse> updatePost(
            @PathVariable("id") long id,
            @Valid @RequestBody UpdatePostRequest updatePostRequest) {
        ResponseEntity<DefaultPostResponse> response = postsClient.updatePost(
                id,
                updatePostRequest.title(),
                updatePostRequest.body(),
                updatePostRequest.userId());
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('POSTS_DELETE', 'ADMIN_DELETE')")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        postsClient.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
