package com.example.task.utils;

import com.example.task.client.jsonplaceholder.dto.albums.DefaultAlbumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AlbumsResponseHandler {
    public String handleAlbum(DefaultAlbumResponse response) {
        if (response != null) {
            return String.format("Album with id %s. User id: %s. Title: %s.",
                    response.id(),
                    response.userId(),
                    response.title());
        } else {
            return "No users found on given id";
        }
    }

    public List<String> handleAlbums(DefaultAlbumResponse[] responses) {
        return Arrays.stream(responses)
                .toList()
                .stream()
                .map(this::handleAlbum)
                .toList();
    }
}