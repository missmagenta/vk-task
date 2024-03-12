package com.example.task;

import com.example.task.auth.AuthenticationService;
import com.example.task.auth.UserConverter;
import com.example.task.auth.UserDetailsImpl;
import com.example.task.auth.dto.UserSignUpRequest;
import com.example.task.auth.jwt.JwtUtils;
import com.example.task.model.official.Manager;
import com.example.task.model.official.ManagerRole;
import com.example.task.model.person.Gender;
import com.example.task.model.person.Person;
import com.example.task.model.user.LoginedUser;
import com.example.task.model.user.User;
import com.example.task.model.user.UserRole;
import com.example.task.payload.BasePayload;
import com.example.task.payload.Payload;
import com.example.task.payload.PayloadWithUser;
import com.example.task.repository.ManagerRepository;
import com.example.task.repository.PersonRepository;
import com.example.task.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseTests {

}
