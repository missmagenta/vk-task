package com.example.task.client.jsonplaceholder;

import com.example.task.client.jsonplaceholder.dto.users.*;
import org.springframework.http.ResponseEntity;

public interface UsersClient {
    GetUserResponse getUser(long id);

    GetUserResponse[] listUsers();

    ResponseEntity<AddUserResponse> addUser(String name,
                                            String username,
                                            String email,
                                            UserAddress address,
                                            String phone,
                                            String website,
                                            UserCompany company);

    ResponseEntity<UpdateUserResponse> updateUser(long id,
                                                  String name,
                                                  String username,
                                                  String email,
                                                  UserAddress address,
                                                  String phone,
                                                  String website,
                                                  UserCompany company);

    ResponseEntity<Void> deleteUser(long id);
}
