package com.example.task.client.jsonplaceholder.impl;

import com.example.task.client.jsonplaceholder.UsersClient;
import com.example.task.client.jsonplaceholder.dto.users.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class UsersClientImpl implements UsersClient {
    private final UsersRepositoryService service;


    @Override
    @Cacheable(cacheNames = "users", key = "#id")
    public GetUserResponse getUser(long id) {
        return service.getUser(id);
    }

    @Override
    public GetUserResponse[] listUsers() {
        return service.listUsers();
    }

    @Override
    @CachePut(cacheNames = "users", key = "#userId")
    public ResponseEntity<AddUserResponse> addUser(String name,
                                                   String username,
                                                   String email,
                                                   UserAddress address,
                                                   String phone,
                                                   String website,
                                                   UserCompany company) {
        return service.addUser(new AddUserRequest(name, username, email, address, phone, website, company));
    }

    @Override
    @CachePut(cacheNames = "users", key = "#id")
    public ResponseEntity<UpdateUserResponse> updateUser(long id,
                                                         String name,
                                                         String username,
                                                         String email,
                                                         UserAddress address,
                                                         String phone,
                                                         String website,
                                                         UserCompany company) {
        return service.updateUser(id, new UpdateUserRequest(id, name, username, email, address, phone, website, company));
    }

    @Override
    public ResponseEntity<Void> deleteUser(long id) {
        return service.deleteUser(id);
    }
}
