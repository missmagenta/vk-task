package com.example.task.auth;


import com.example.task.model.official.Manager;
import com.example.task.model.user.User;
import com.example.task.repository.ManagerRepository;
import com.example.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.task.auth.UserConverter.getRoleByManager;

/**
 * Service class that implements the UserDetailsService interface to provide user-related functionality.
 */
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }

        Manager manager = managerRepository.getCurrentByPersonId(user.getPersonId());
        user.setRole(getRoleByManager(manager));
        return UserDetailsImpl.build(user);
    }

    public User getUser() {
        String username =
                ((UsernamePasswordAuthenticationToken) SecurityContextHolder
                        .getContext()
                        .getAuthentication()).getName();
        return userRepository.findByUsername(username);

    }
}
