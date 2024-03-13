package com.example.task.client.jsonplaceholder;

import com.example.task.client.jsonplaceholder.dto.albums.AddAlbumResponse;
import com.example.task.client.jsonplaceholder.dto.albums.DefaultAlbumResponse;
import com.example.task.client.jsonplaceholder.dto.albums.UpdateAlbumResponse;
import org.springframework.http.ResponseEntity;
/**
 * Client interface for interacting with the external JSONPlaceholder API (jsonplaceholder.typicode.com/albums).
 * Provides methods for retrieving, adding, updating, and deleting albums.
 */
public interface AlbumsClient {
    DefaultAlbumResponse getAlbum(long id);
    DefaultAlbumResponse[] listAlbums();
    DefaultAlbumResponse[] getAlbumsByUser(long userId);
    ResponseEntity<AddAlbumResponse> addAlbum(long userId, String title);
    ResponseEntity<UpdateAlbumResponse> updateAlbum(long id, long userId, String title);
    ResponseEntity<Void> deleteAlbum(long id);
}
