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

    /**
     * Gets the user role based on the role of the provided Manager entity.
     *
     * @param manager The Manager entity whose role needs to be determined.
     * @return The UserRole corresponding to the role of the manager.
     *         Returns UserRole.INVALID if the manager is null or the role is unrecognized.
     */

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
