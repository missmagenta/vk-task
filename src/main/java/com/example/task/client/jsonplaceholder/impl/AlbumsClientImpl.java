package com.example.task.client.jsonplaceholder.impl;

import com.example.task.client.jsonplaceholder.AlbumsClient;
import com.example.task.client.jsonplaceholder.dto.albums.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class AlbumsClientImpl implements AlbumsClient {

    private final AlbumsRepositoryService service;

    @Override
    public AlbumResponse getAlbum(long id) {
        return service.getAlbum(id);
    }

    @Override
    public AlbumResponse[] listAlbums() {
        return service.listAlbums();
    }

    @Override
    public AlbumResponse[] getAlbumsByUser(long userId) {
        return service.getAlbumsByUser(userId);
    }

    @Override
    public ResponseEntity<AddAlbumResponse> addAlbum(long userId, String title) {
        return service.addAlbum(new AddAlbumRequest(userId, title));
    }

    @Override
    public ResponseEntity<UpdateAlbumResponse> updateAlbum(long id, long userId, String title) {
        return service.updateAlbum(id, new UpdateAlbumRequest(userId, id, title));
    }

    @Override
    public Void deleteAlbum(long id) {
        return service.deleteAlbum(id);
    }
}
