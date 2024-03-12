package com.example.task.controller;

import com.example.task.client.jsonplaceholder.UsersClient;
import com.example.task.client.jsonplaceholder.dto.posts.AddPostRequest;
import com.example.task.client.jsonplaceholder.dto.posts.PostResponse;
import com.example.task.client.jsonplaceholder.dto.users.AddUserRequest;
import com.example.task.client.jsonplaceholder.dto.users.AddUserResponse;
import com.example.task.client.jsonplaceholder.dto.users.GetUserResponse;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('USERS')")
public class UsersController {
    private final UsersClient usersClient;

    public UsersController(UsersClient usersClient) {
        this.usersClient = usersClient;
    }

    @GetMapping("/{id}")
    @CachePut(cacheNames = "users", key = "#id")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("id") long id) {
        GetUserResponse response = usersClient.getUser(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<GetUserResponse[]> listUsers() {
        GetUserResponse[] posts = usersClient.listUsers();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<AddUserResponse> addUser(@Valid @RequestBody AddUserRequest addUserRequest) {
        ResponseEntity<AddUserResponse> response = usersClient.addUser(
                addUserRequest.name(),
                addUserRequest.username(),
                addUserRequest.email(),
                addUserRequest.address(),
                addUserRequest.phone(),
                addUserRequest.website(),
                addUserRequest.company());
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
