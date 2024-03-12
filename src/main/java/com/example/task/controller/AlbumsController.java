package com.example.task.controller;

import com.example.task.client.jsonplaceholder.AlbumsClient;
import com.example.task.client.jsonplaceholder.dto.albums.AddAlbumRequest;
import com.example.task.client.jsonplaceholder.dto.albums.AddAlbumResponse;
import com.example.task.client.jsonplaceholder.dto.albums.AlbumResponse;
import com.example.task.client.jsonplaceholder.dto.users.AddUserRequest;
import com.example.task.client.jsonplaceholder.dto.users.AddUserResponse;
import com.example.task.client.jsonplaceholder.dto.users.GetUserResponse;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
@PreAuthorize("hasRole('ALBUMS')")
public class AlbumsController {
    private final AlbumsClient albumsClient;

    public AlbumsController(AlbumsClient albumsClient) {
        this.albumsClient = albumsClient;
    }

    @GetMapping("/{id}")
    @CachePut(cacheNames = "albums", key = "#id")
    public ResponseEntity<AlbumResponse> getAlbum(@PathVariable("id") long id) {
        AlbumResponse response = albumsClient.getAlbum(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<AlbumResponse[]> listAlbums() {
        AlbumResponse[] posts = albumsClient.listAlbums();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<AddAlbumResponse> addAlbums(@Valid @RequestBody AddAlbumRequest addAlbumRequest) {
        ResponseEntity<AddAlbumResponse> response = albumsClient.addAlbum(
                addAlbumRequest.userId(),
                addAlbumRequest.title());
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
