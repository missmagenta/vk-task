package com.example.task.auth;

import com.example.task.auth.dto.UserAuthRequest;
import com.example.task.auth.dto.UserSignUpRequest;
import com.example.task.auth.jwt.JwtUtils;
import com.example.task.model.official.Manager;
import com.example.task.model.person.Person;
import com.example.task.model.user.LoginedUser;
import com.example.task.model.user.UserRole;
import com.example.task.payload.BasePayload;
import com.example.task.payload.Payload;
import com.example.task.model.user.User;
import com.example.task.payload.PayloadWithUser;
import com.example.task.auth.dto.UserPayload;
import com.example.task.repository.ManagerRepository;
import com.example.task.repository.PersonRepository;
import com.example.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.task.auth.UserConverter.getRoleByManager;
import static com.example.task.repository.PersonRepository.allFieldsExceptId;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final ManagerRepository managerRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public Payload authenticateUser(UserAuthRequest loginUser) {
        User user = userRepository.findByUsername(loginUser.username());
        if (user == null) {
            return new BasePayload(400, "Пользователь не найден.");
        }

        LoginedUser loginedUser = login(user.getUsername(), loginUser.password());
        Person person = personRepository.find(user.getPersonId());
        Manager manager = managerRepository.getCurrentByPersonId(person.getId());
        UserRole role = getRoleByManager(manager);
        Integer officialId = null;
        if (manager != null) {
            officialId = manager.getId();
        }

        UserPayload userPayload = new UserPayload(
                loginedUser.username(),
                role,
                loginedUser.jwtToken(),
                person.getId(),
                officialId,
                person.getName() + " " + person.getSurname(),
                person.getEmail()

        );

        return new PayloadWithUser(200, userPayload);
    }

    public Payload registerUser(UserSignUpRequest signupUser) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signupUser.getUsername()))) {
            return new BasePayload(400, "Имя пользователя уже занято.");
        }

        Person person = personRepository.findByCondition(
                allFieldsExceptId(
                        signupUser.getName(),
                        signupUser.getSurname(),
                        signupUser.getBirthDate(),
                        signupUser.getPersonGender(),
                        signupUser.getEmail()
                )
        );
        if (person == null) {
            return new BasePayload(400, "Пользователь не найден.");
        }
        if (userRepository.findByPerson(person.getId()) != null) {
            return new BasePayload(400, "Аккаунт уже существует");
        }
        Manager manager = managerRepository.getCurrentByPersonId(person.getId());
        UserRole role = getRoleByManager(manager);
        Integer officialId = null;
        if (manager != null) {
            officialId = manager.getId();
        }

        User user = new User();
        user.setPersonId(person.getId());
        user.setUsername(signupUser.getUsername());
        user.setPassword(encoder.encode(signupUser.getPassword()));
        user.setRole(role);

        userRepository.insert(user);
        LoginedUser loginedUser = login(user.getUsername(), signupUser.getPassword());
        UserPayload userPayload = new UserPayload(
                loginedUser.username(),
                role,
                loginedUser.jwtToken(),
                person.getId(),
                officialId,
                person.getName() + " " + person.getSurname(),
                person.getEmail()
        );

        return new PayloadWithUser(200, userPayload);
    }

    private LoginedUser login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return UserConverter.convertToUserDTO(userDetails, jwt);
    }
}
