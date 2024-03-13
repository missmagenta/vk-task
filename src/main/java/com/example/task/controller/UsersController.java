package com.example.task.controller;

import com.example.task.client.jsonplaceholder.UsersClient;
import com.example.task.client.jsonplaceholder.dto.users.*;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

@RestController
@RequestMapping("/users")
//@PreAuthorize("hasRole('USERS')")
public class UsersController {
    private final UsersClient usersClient;

    public UsersController(UsersClient usersClient) {
        this.usersClient = usersClient;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USERS_VIEW', 'ADMIN_VIEW')")
    @CachePut(cacheNames = "users", key = "#id")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("id") long id) {
        GetUserResponse response = usersClient.getUser(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USERS_VIEW', 'ADMIN_VIEW')")
    public ResponseEntity<GetUserResponse[]> listUsers() {
        GetUserResponse[] posts = usersClient.listUsers();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USERS_CREATE', 'ADMIN_CREATE')")
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

    @PutExchange("/{id}")
    @PreAuthorize("hasAnyRole('USERS_UPDATE', 'ADMIN_UPDATE')")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @PathVariable("id") long id,
            @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        ResponseEntity<UpdateUserResponse> response = usersClient.updateUser(
                id,
                updateUserRequest.name(),
                updateUserRequest.username(),
                updateUserRequest.email(),
                updateUserRequest.address(),
                updateUserRequest.phone(),
                updateUserRequest.website(),
                updateUserRequest.company());
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USERS_DELETE', 'ADMIN_DELETE')")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        usersClient.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
