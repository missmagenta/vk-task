package com.example.task.client.jsonplaceholder;


import com.example.task.client.jsonplaceholder.dto.posts.DefaultPostResponse;
import org.springframework.http.ResponseEntity;

public interface PostsClient {
    DefaultPostResponse getPost(long id);

    DefaultPostResponse[] listPosts();

    ResponseEntity<DefaultPostResponse> addPost(String title, String body, long userId);

    ResponseEntity<DefaultPostResponse> updatePost(long id, String title, String body, long userId);

    ResponseEntity<Void> deletePost(long id);
}
