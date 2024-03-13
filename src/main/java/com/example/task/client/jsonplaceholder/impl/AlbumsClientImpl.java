package com.example.task.client.jsonplaceholder.impl;

import com.example.task.client.jsonplaceholder.AlbumsClient;
import com.example.task.client.jsonplaceholder.dto.albums.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class AlbumsClientImpl implements AlbumsClient {

    private final AlbumsRepositoryService service;

    @Override
    @Cacheable(cacheNames = "albums", key = "#id")
    public DefaultAlbumResponse getAlbum(long id) {
        return service.getAlbum(id);
    }

    @Override
    public DefaultAlbumResponse[] listAlbums() {
        return service.listAlbums();
    }

    @Override
    @Cacheable(cacheNames = "albums", key = "#userId")
    public DefaultAlbumResponse[] getAlbumsByUser(long userId) {
        return service.getAlbumsByUser(userId);
    }

    @Override
    @CachePut(cacheNames = "albums", key = "#userId")
    public ResponseEntity<AddAlbumResponse> addAlbum(long userId,
                                                     String title) {
        return service.addAlbum(new AddAlbumRequest(userId, title));
    }

    @Override
    @CachePut(cacheNames = "albums", key = "#userId")
    public ResponseEntity<UpdateAlbumResponse> updateAlbum(long id,
                                                           long userId,
                                                           String title) {
        return service.updateAlbum(id, new UpdateAlbumRequest(userId, id, title));
    }

    @Override
    public ResponseEntity<Void> deleteAlbum(long id) {
        return service.deleteAlbum(id);
    }
}
