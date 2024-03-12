package com.example.task.model.user;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private Integer personId;
    private String username;
    private String password;
    private UserRole role;
    private String jwtToken;
}
