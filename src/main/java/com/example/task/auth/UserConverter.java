package com.example.task.auth;


import com.example.task.model.official.Manager;
import com.example.task.model.user.LoginedUser;
import com.example.task.model.user.User;
import com.example.task.model.user.UserRole;

public final class UserConverter {
    private UserConverter() {}

    public static LoginedUser convertToUserDTO(UserDetailsImpl userDetails, String jwt) {
        UserRole userRole = UserRole.valueOf(userDetails.getAuthority().getAuthority());

        return new LoginedUser(userDetails.getUsername(), userRole, jwt);
    }

    public static LoginedUser convertToUserDTO(User user) {
        return new LoginedUser(user.getUsername(), user.getRole(), "");
    }

    public static UserRole getRoleByManager(Manager manager) {
        if (manager == null) return UserRole.INVALID;
        switch (manager.getManagerRole()) {
            case ADMIN -> {
                return UserRole.ADMIN;
            }
            case POSTS_MANAGER -> {
                return UserRole.POSTS;
            }
            case USERS_MANAGER -> {
                return UserRole.USERS;
            }
            case ALBUMS_MANAGER -> {
                return UserRole.ALBUMS;
            }
            default -> {
                return UserRole.INVALID;
            }
        }
    }
}
