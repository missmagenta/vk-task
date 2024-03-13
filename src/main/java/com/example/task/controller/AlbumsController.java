package com.example.task.controller;

import com.example.task.client.jsonplaceholder.AlbumsClient;
import com.example.task.client.jsonplaceholder.dto.albums.*;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumsController {
    private final AlbumsClient albumsClient;

    public AlbumsController(AlbumsClient albumsClient) {
        this.albumsClient = albumsClient;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ALBUMS_VIEW', 'ADMIN_VIEW')")
    @CachePut(cacheNames = "albums", key = "#id")
    public ResponseEntity<DefaultAlbumResponse> getAlbum(@PathVariable("id") long id) {
        DefaultAlbumResponse response = albumsClient.getAlbum(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ALBUMS_VIEW', 'ADMIN_VIEW')")
    public ResponseEntity<DefaultAlbumResponse[]> listAlbums() {
        DefaultAlbumResponse[] posts = albumsClient.listAlbums();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ALBUMS_CREATE', 'ADMIN_CREATE')")
    public ResponseEntity<AddAlbumResponse> addAlbums(@Valid @RequestBody AddAlbumRequest addAlbumRequest) {
        ResponseEntity<AddAlbumResponse> response = albumsClient.addAlbum(
                addAlbumRequest.userId(),
                addAlbumRequest.title());
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ALBUMS_UPDATE', 'ADMIN_UPDATE')")
    public ResponseEntity<UpdateAlbumResponse> updatePost(
            @PathVariable("id") long id,
            @Valid @RequestBody UpdateAlbumRequest updateAlbumRequest) {
        ResponseEntity<UpdateAlbumResponse> response = albumsClient.updateAlbum(
                id,
                updateAlbumRequest.userId(),
                updateAlbumRequest.title());
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ALBUMS_DELETE', 'ADMIN_DELETE')")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        albumsClient.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
