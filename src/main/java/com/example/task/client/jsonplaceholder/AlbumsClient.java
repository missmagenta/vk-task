package com.example.task.client.jsonplaceholder;

import com.example.task.client.jsonplaceholder.dto.albums.AddAlbumResponse;
import com.example.task.client.jsonplaceholder.dto.albums.AlbumResponse;
import com.example.task.client.jsonplaceholder.dto.albums.ListAlbumResponse;
import com.example.task.client.jsonplaceholder.dto.albums.UpdateAlbumResponse;
import org.springframework.http.ResponseEntity;

public interface AlbumsClient {
    AlbumResponse getAlbum(long id);
    AlbumResponse[] listAlbums();
    AlbumResponse[] getAlbumsByUser(long userId);
    ResponseEntity<AddAlbumResponse> addAlbum(long userId, String title);
    ResponseEntity<UpdateAlbumResponse> updateAlbum(long id, long userId, String title);
    Void deleteAlbum(long id);
}
