package com.example.task.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.example.task.model.user.ExtendedUserRole.*;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    ADMIN(Set.of(
            POSTS_VIEW,
            POSTS_CREATE,
            POSTS_UPDATE,
            POSTS_DELETE,
            USERS_VIEW,
            USERS_CREATE,
            USERS_UPDATE,
            USERS_DELETE,
            ALBUMS_VIEW,
            ALBUMS_CREATE,
            ALBUMS_UPDATE,
            ALBUMS_DELETE)),
    POSTS(Set.of(POSTS_VIEW, POSTS_CREATE, POSTS_UPDATE, POSTS_DELETE)),
    USERS(Set.of(USERS_VIEW, USERS_CREATE, USERS_UPDATE, USERS_DELETE)),
    ALBUMS(Set.of(ALBUMS_VIEW, ALBUMS_CREATE, ALBUMS_UPDATE, ALBUMS_DELETE)),
    INVALID(Set.of(INVALID_ACCESS));

    private final Set<ExtendedUserRole> subRoles;
}
