package com.example.task.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    ADMIN(Set.of(
            "POSTS_VIEWER",
            "POSTS_EDITOR",
            "USERS_VIEWER",
            "USERS_EDITOR",
            "ALBUMS_VIEWER",
            "ALBUMS_EDITOR")),
    POSTS(Set.of("POSTS_VIEWER", "POSTS_EDITOR")),
    USERS(Set.of("USERS_VIEWER", "USERS_EDITOR")),
    ALBUMS(Set.of("ALBUMS_VIEWER", "ALBUMS_EDITOR")),
    INVALID(Set.of("INVALID"));

    private final Set<String> subRoles;
}
