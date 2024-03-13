package com.example.task.clients;

import com.example.task.client.jsonplaceholder.AlbumsClient;
import com.example.task.client.jsonplaceholder.dto.albums.*;
import com.example.task.configuration.ClientConfig;
import com.example.task.utils.AlbumsResponseHandler;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ClientConfig.class)
public class AlbumsClientTest {
    @Autowired
    private AlbumsClient albumsClient;
    private AlbumsResponseHandler albumsResponseHandler;

    @Before
    public void setUp() {
        albumsResponseHandler = new AlbumsResponseHandler();
    }

    @Test
    public void testGetAlbum() {
        DefaultAlbumResponse album = albumsClient.getAlbum(11);
        String formatted = albumsResponseHandler.handleAlbum(album);
        assertEquals("Album with id 11. User id: 2. " +
                "Title: quam nostrum impedit mollitia quod et dolor.", formatted);
    }

    @Test
    public void testGetAlbumsByUser() {
        DefaultAlbumResponse[] albums = albumsClient.getAlbumsByUser(1);
        List<String> formatted = albumsResponseHandler.handleAlbums(albums);
        assertEquals(10, formatted.size());
    }

    @Test
    public void testListAlbums() {
        DefaultAlbumResponse[] albums = albumsClient.listAlbums();
        List<String> formatted = albumsResponseHandler.handleAlbums(albums);
        assertEquals(100, formatted.size());
    }

    @Test
    public void testAddAlbum() {
        AddAlbumRequest addAlbumRequest = new AddAlbumRequest(2, "testtest");
        ResponseEntity<AddAlbumResponse> responseEntity = albumsClient.addAlbum(
                addAlbumRequest.userId(),
                addAlbumRequest.title());
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode()),
                () -> assertNotNull(responseEntity.getBody())
        );
    }

    @Test
    public void testUpdateAlbum() {
        long userId = 2;
        long albumIdToUpdate = 16;
        UpdateAlbumRequest updateAlbumRequest = new UpdateAlbumRequest(
                userId,
                albumIdToUpdate,
                "updated");
        ResponseEntity<UpdateAlbumResponse> responseEntity = albumsClient.updateAlbum(
                updateAlbumRequest.id(),
                updateAlbumRequest.userId(),
                updateAlbumRequest.title()
        );
        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertEquals(16, Objects.requireNonNull(responseEntity.getBody()).id()),
                () -> assertEquals("updated", Objects.requireNonNull(responseEntity.getBody()).title())
        );
    }

    @Test
    public void testDeleteAlbum() {
        long albumIdToDelete = 1;
        ResponseEntity<Void> responseEntity = albumsClient.deleteAlbum(albumIdToDelete);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}