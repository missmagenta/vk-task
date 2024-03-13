package com.example.task.client.jsonplaceholder.impl;

import com.example.task.client.jsonplaceholder.dto.albums.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;
/**
 * HTTP interface for interacting with the Albums Repository service.
 * Provides methods for retrieving, adding, updating, and deleting albums.
 */
public interface AlbumsRepositoryService {
    @GetExchange("/albums/{id}")
    DefaultAlbumResponse getAlbum(@PathVariable("id") long id);

    @GetExchange("/albums")
    DefaultAlbumResponse[] listAlbums();

    @GetExchange("/albums")
    DefaultAlbumResponse[] getAlbumsByUser(@RequestParam("userId") long userId);

    @PostExchange("/albums")
    ResponseEntity<AddAlbumResponse> addAlbum(
            @RequestBody AddAlbumRequest addAlbumRequest
    );

    @PutExchange("/albums/{id}")
    ResponseEntity<UpdateAlbumResponse> updateAlbum(
            @PathVariable("id") long id,
            @RequestBody UpdateAlbumRequest updateAlbumRequest
    );

    @DeleteExchange("albums/{id}")
    ResponseEntity<Void> deleteAlbum(@PathVariable("id") long id);
}
