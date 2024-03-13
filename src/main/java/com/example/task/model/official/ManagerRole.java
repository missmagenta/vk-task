package com.example.task.model.official;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ManagerRole {
    ADMIN("ADMIN"),
    POSTS_MANAGER("POSTS_MANAGER"),
    USERS_MANAGER("USERS_MANAGER"),
    ALBUMS_MANAGER("ALBUMS_MANAGER");

    private final String displayRole;
}
