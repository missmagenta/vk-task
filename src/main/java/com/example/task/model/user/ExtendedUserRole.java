package com.example.task.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExtendedUserRole {

    ADMIN_VIEW("ADMIN: VIEW"),
    ADMIN_CREATE("ADMIN: CREATE"),
    ADMIN_UPDATE("ADMIN: UPDATE"),
    ADMIN_DELETE("ADMIN: DELETE"),

    POSTS_VIEW("POSTS: VIEW"),
    POSTS_CREATE("POSTS: CREATE"),
    POSTS_UPDATE("POSTS: UPDATE"),
    POSTS_DELETE("POSTS: DELETE"),

    USERS_VIEW("USERS: VIEW"),
    USERS_CREATE("USERS: CREATE"),
    USERS_UPDATE("USERS: UPDATE"),
    USERS_DELETE("USERS: DELETE"),

    ALBUMS_VIEW("ALBUMS: VIEW"),
    ALBUMS_CREATE("ALBUMS: CREATE"),
    ALBUMS_UPDATE("ALBUMS: UPDATE"),
    ALBUMS_DELETE("ALBUMS: DELETE"),
    INVALID_ACCESS("INVALID");

    private final String permission;
}