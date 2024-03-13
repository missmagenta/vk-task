package com.example.task.client.jsonplaceholder.impl;

import com.example.task.client.jsonplaceholder.dto.users.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

public interface UsersRepositoryService {
    @GetExchange("/users/{id}")
    GetUserResponse getUser(@PathVariable("id") long id);

    @GetExchange("/users")
    GetUserResponse[] listUsers();

    @PostExchange("/users")
    ResponseEntity<AddUserResponse> addUser(
            @RequestBody AddUserRequest addUserRequest
    );

    @PutExchange("/users/{id}")
    ResponseEntity<UpdateUserResponse> updateUser(
            @PathVariable("id") long id,
            @RequestBody UpdateUserRequest updateUserRequest
    );

    @DeleteExchange("/users/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") long id);
}
