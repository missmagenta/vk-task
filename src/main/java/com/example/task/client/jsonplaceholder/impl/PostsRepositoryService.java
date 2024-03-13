package com.example.task.client.jsonplaceholder.impl;

import com.example.task.client.jsonplaceholder.dto.posts.AddPostRequest;
import com.example.task.client.jsonplaceholder.dto.posts.DefaultPostResponse;
import com.example.task.client.jsonplaceholder.dto.posts.UpdatePostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;
/**
 * HTTP interface for interacting with the Posts Repository service.
 * Provides methods for retrieving, adding, updating, and deleting posts.
 */
public interface PostsRepositoryService {

    @GetExchange("/posts/{id}")
    DefaultPostResponse getPost(@PathVariable("id") long id);

    @GetExchange("/posts")
    DefaultPostResponse[] listPosts();

    @PostExchange("/posts")
    ResponseEntity<DefaultPostResponse> addPost(
            @RequestBody AddPostRequest addPostRequest);

    @PutExchange("/posts/{id}")
    ResponseEntity<DefaultPostResponse> updatePost(
            @PathVariable("id") long id,
            @RequestBody UpdatePostRequest updatePostRequest);

    @DeleteExchange("/posts/{id}")
    ResponseEntity<Void> deletePost(@PathVariable("id") long id);
}
