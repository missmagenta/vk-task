package com.example.task.clients;

import com.example.task.client.jsonplaceholder.PostsClient;
import com.example.task.client.jsonplaceholder.dto.posts.AddPostRequest;
import com.example.task.client.jsonplaceholder.dto.posts.DefaultPostResponse;
import com.example.task.client.jsonplaceholder.dto.posts.UpdatePostRequest;
import com.example.task.configuration.ClientConfig;
import com.example.task.utils.PostsResponseHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ClientConfig.class)
public class PostsClientTest {
    @Autowired
    private PostsClient postsClient;
    private PostsResponseHandler postsResponseHandler;

    @Before
    public void setUp() {
        postsResponseHandler = new PostsResponseHandler();
    }

    @Test
    public void testGetPost() {
        DefaultPostResponse post = postsClient.getPost(1);
        String formatted = postsResponseHandler.handlePost(post);
        assertEquals("Post with id 1. User id - 1. " +
                "The title: sunt aut facere repellat provident occaecati excepturi optio reprehenderit. " +
                "Contents: quia et suscipit\n" +
                "suscipit recusandae consequuntur expedita et cum\n" +
                "reprehenderit molestiae ut ut quas totam\n" +
                "nostrum rerum est autem sunt rem eveniet architecto", formatted);
    }

    @Test
    public void testListPosts() {
        DefaultPostResponse[] posts = postsClient.listPosts();
        List<String> formatted = postsResponseHandler.handlePosts(posts);
        assertEquals(100, formatted.size());
    }

    @Test
    public void testAddPost() {
        AddPostRequest addPostRequest = new AddPostRequest("test", "testtest", 5);
        ResponseEntity<DefaultPostResponse> responseEntity = postsClient.addPost(
                addPostRequest.title(),
                addPostRequest.body(),
                addPostRequest.userId());
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode()),
                () -> assertNotNull(responseEntity.getBody())
        );
    }

    @Test
    public void testUpdatePost() {
        long postIdToUpdate = 100;
        UpdatePostRequest updatePostRequest = new UpdatePostRequest(
                postIdToUpdate,
                "updated",
                "updated body",
                10);
        ResponseEntity<DefaultPostResponse> responseEntity = postsClient.updatePost(
                postIdToUpdate,
                updatePostRequest.title(),
                updatePostRequest.body(),
                updatePostRequest.userId());
        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertEquals(100, Objects.requireNonNull(responseEntity.getBody()).id()),
                () -> assertEquals("updated", Objects.requireNonNull(responseEntity.getBody()).title()),
                () -> assertEquals("updated body", Objects.requireNonNull(responseEntity.getBody()).body()),
                () -> assertEquals(10, Objects.requireNonNull(responseEntity.getBody()).userId())
        );
    }

    @Test
    public void testDeletePost() {
        long postIdToDelete = 1;
        ResponseEntity<Void> responseEntity = postsClient.deletePost(postIdToDelete);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
