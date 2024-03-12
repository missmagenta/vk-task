package com.example.task.configuration;


import com.example.task.auth.UserDetailsServiceImpl;
import com.example.task.auth.jwt.AuthEntryPointJwt;
import com.example.task.auth.jwt.AuthTokenFilter;
import com.example.task.model.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.task.model.user.UserRole.*;
import static com.example.task.model.user.ExtendedUserRole.*;
import static org.springframework.http.HttpMethod.*;

@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;
    private static final String POSTS_API_PATTERN = "/api/posts/**";
    private static final String USERS_API_PATTERN = "/api/users/**";
    private static final String ALBUMS_API_PATTERN = "/api/albums/**";

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/auth/**").permitAll()

                                .requestMatchers(POSTS_API_PATTERN).hasAnyRole(
                                        ADMIN.name(), POSTS.name())
                                .requestMatchers(GET,POSTS_API_PATTERN).hasAnyRole(
                                        ADMIN_VIEW.name(), POSTS_VIEW.name())
                                .requestMatchers(POST,POSTS_API_PATTERN).hasAnyRole(
                                        ADMIN_CREATE.name(), POSTS_CREATE.name())
                                .requestMatchers(PUT,POSTS_API_PATTERN).hasAnyRole(
                                        ADMIN_UPDATE.name(), POSTS_UPDATE.name())
                                .requestMatchers(DELETE,POSTS_API_PATTERN).hasAnyRole(
                                        ADMIN_DELETE.name(), POSTS_DELETE.name())


                                .requestMatchers(USERS_API_PATTERN).hasAnyRole(
                                        ADMIN.name(), USERS.name())
                                .requestMatchers(GET, USERS_API_PATTERN).hasAnyRole(
                                        ADMIN_VIEW.name(), USERS_VIEW.name())
                                .requestMatchers(POST, USERS_API_PATTERN).hasAnyRole(
                                        ADMIN_CREATE.name(), USERS_CREATE.name())
                                .requestMatchers(PUT,USERS_API_PATTERN).hasAnyRole(
                                        ADMIN_UPDATE.name(), USERS_UPDATE.name())
                                .requestMatchers(DELETE, USERS_API_PATTERN).hasAnyRole(
                                        ADMIN_DELETE.name(), USERS_DELETE.name())


                                .requestMatchers(ALBUMS_API_PATTERN).hasAnyRole(
                                        ADMIN.name(), ALBUMS.name())
                                .requestMatchers(GET, ALBUMS_API_PATTERN).hasAnyRole(
                                        ADMIN_VIEW.name(), ALBUMS_VIEW.name())
                                .requestMatchers(POST, ALBUMS_API_PATTERN).hasAnyRole(
                                        ADMIN_CREATE.name(), ALBUMS_CREATE.name())
                                .requestMatchers(PUT, ALBUMS_API_PATTERN).hasAnyRole(
                                        ADMIN_UPDATE.name(), ALBUMS_UPDATE.name())
                                .requestMatchers(DELETE, ALBUMS_API_PATTERN).hasAnyRole(
                                        ADMIN_DELETE.name(), ALBUMS_DELETE.name())


                                .requestMatchers("/ws/**").permitAll()
                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
