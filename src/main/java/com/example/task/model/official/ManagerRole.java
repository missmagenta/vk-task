package com.example.task.model.official;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ManagerRole {
    ADMIN("Admin"),
    POSTS_MANAGER("PostsManager"),
    USERS_MANAGER("UsersManager"),
    ALBUMS_MANAGER("AlbumsManager");

    private final String displayRole;
}
