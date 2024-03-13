package com.example.task.auth;
import com.example.task.auth.UserDetailsServiceImpl;
import com.example.task.auth.jwt.AuthTokenFilter;
import com.example.task.auth.jwt.JwtUtils;
import com.example.task.model.audit.Status;
import com.example.task.service.AuditLogService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class JwtFilterTest {
    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private AuditLogService auditLogService;


    private AuthTokenFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtAuthenticationFilter = new AuthTokenFilter();
        ReflectionTestUtils.setField(jwtAuthenticationFilter, "jwtUtils", jwtUtils);
        ReflectionTestUtils.setField(jwtAuthenticationFilter, "userDetailsService", userDetailsService);
        ReflectionTestUtils.setField(jwtAuthenticationFilter, "auditLogService", auditLogService);
    }

    @Test
    void doFilterInternal__callAllMethodsInDoFilterInternal_verifyAll() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        UserDetails userDetails = mock(UserDetails.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer fakeToken");
        when(jwtUtils.getUserNameFromJwtToken("fakeToken")).thenReturn("testUser");
        when(userDetailsService.loadUserByUsername("testUser")).thenReturn(userDetails);
        when(jwtUtils.validateJwtToken("fakeToken")).thenReturn(true);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verify(userDetailsService).loadUserByUsername("testUser");
        verify(jwtUtils).validateJwtToken("fakeToken");
        verify(userDetails).getAuthorities();
        verify(auditLogService).logRequest(Status.SUCCEEDED);
    }
}
