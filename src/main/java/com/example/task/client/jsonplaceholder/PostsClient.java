package com.example.task.client.jsonplaceholder;


import com.example.task.client.jsonplaceholder.dto.posts.PostResponse;
import org.springframework.http.ResponseEntity;

public interface PostsClient {
    PostResponse getPost(long id);

    PostResponse[] listPosts();

    ResponseEntity<PostResponse> addPost(String title, String body, long userId);

    ResponseEntity<PostResponse> updatePost(long id, String title, String body, long userId);

    Void deletePost(long id);
}
