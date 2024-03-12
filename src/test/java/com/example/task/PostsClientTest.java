package com.example.task;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.task.client.jsonplaceholder.PostsClient;
import com.example.task.client.jsonplaceholder.dto.posts.AddPostRequest;
import com.example.task.client.jsonplaceholder.dto.posts.PostResponse;
import com.example.task.client.jsonplaceholder.dto.posts.UpdatePostRequest;
import com.example.task.configuration.ClientConfig;
import com.example.task.service.PostsResponseHandler;
import com.example.task.service.SlowServiceWithCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ClientConfig.class)
public class PostsClientTest {
//    private ClientConfig clientConfig;
    @Autowired
    private PostsClient postsClient;
    private PostsResponseHandler postsResponseHandler;

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private SlowServiceWithCache slowServiceWithCache;

    @Before
    public void setUp() {
//        clientConfig =  new ClientConfig();
//        postsClient = clientConfig.postsClient();
        postsResponseHandler = new PostsResponseHandler();
//        cacheManager = new CaffeineCacheManager();
    }

    @Test
    public void testGetPost() {
        PostResponse post = postsClient.getPost(1);
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
        PostResponse[] posts = postsClient.listPosts();
        List<String> formatted = postsResponseHandler.handlePosts(posts);
        assertEquals(100, formatted.size());
        assertNotNull(posts);
    }

    @Test
    public void testAddPost() {
        AddPostRequest addPostRequest = new AddPostRequest("test", "testtest", 5);
        ResponseEntity<PostResponse> responseEntity = postsClient.addPost(
                addPostRequest.title(),
                addPostRequest.body(),
                addPostRequest.userId());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void testUpdatePost() {
        long postIdToUpdate = 100;
        UpdatePostRequest updatePostRequest = new UpdatePostRequest(postIdToUpdate, "updated", "updated body", 10);
        ResponseEntity<PostResponse> responseEntity = postsClient.updatePost(
                postIdToUpdate,
                updatePostRequest.title(),
                updatePostRequest.body(),
                updatePostRequest.userId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test

    public void testCacheUsage() {
        // Clear the cache to ensure a clean state
//        Cache cache = Objects.requireNonNull(cacheManager.getCache("posts"));
//        cache.clear();


        postsClient.getPost(1);
        postsClient.getPost(1);

        System.out.println(postsClient.getPost(1));

        // Check if the cache contains the post with ID 1
//        assertNotNull(post);
        Cache<Object, Object> caffeine = getNativeCaffeineCache();
        System.out.println(caffeine.stats());
        System.out.println(caffeine.asMap());
    }

    private Cache<Object, Object> getNativeCaffeineCache() {
        CaffeineCacheManager caffeineCacheManager = (CaffeineCacheManager) cacheManager;
        CaffeineCache cache = (CaffeineCache) caffeineCacheManager.getCache("posts");
        return cache.getNativeCache();
    }

    @Test
    public void givenCaffeineCacheCachingSlowCalls_whenCacheManagerProperlyCasted_thenAllKeysAreAccessible() {
        slowServiceWithCache.save("first", "some-value-first");
        slowServiceWithCache.save("second", "other-value-second");

        Cache<Object, Object> caffeine = getNativeCaffeineCacheForSlowService();
        System.out.println(caffeine.asMap());
        assertThat(caffeine.asMap().keySet()).containsOnly("first", "second");
    }

    private Cache<Object, Object> getNativeCaffeineCacheForSlowService() {
        CaffeineCacheManager caffeineCacheManager = (CaffeineCacheManager) cacheManager;
        CaffeineCache cache = (CaffeineCache) caffeineCacheManager.getCache("slowServiceCache");
        return cache.getNativeCache();
    }



}
