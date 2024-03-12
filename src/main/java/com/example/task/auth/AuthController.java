package com.example.task.auth;

import com.example.task.auth.dto.UserAuthRequest;
import com.example.task.auth.dto.UserSignUpRequest;
import com.example.task.payload.Payload;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(
        value="/auth",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/signin",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payload> authenticateUser(@Valid @RequestBody UserAuthRequest loginRequest) {
        Payload payload = authenticationService.authenticateUser(loginRequest);

        if (payload.code() == 400) {
            return ResponseEntity
                    .badRequest()
                    .body(payload);
        }
        return ResponseEntity.ok(payload);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Payload> registerUser(@Valid @RequestBody UserSignUpRequest signUpRequest) {
        Payload payload = authenticationService.registerUser(signUpRequest);

        if (payload.code() == 400) {
            return ResponseEntity
                    .badRequest()
                    .body(payload);
        }
        return ResponseEntity.ok(payload);
    }
}
