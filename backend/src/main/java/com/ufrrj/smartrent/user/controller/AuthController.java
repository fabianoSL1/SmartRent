package com.ufrrj.smartrent.user.controller;

import com.ufrrj.smartrent.user.dtos.LoginRequest;
import com.ufrrj.smartrent.user.dtos.LoginResponse;
import com.ufrrj.smartrent.user.dtos.RegisterRequest;
import com.ufrrj.smartrent.user.dtos.RegisterResponse;
import com.ufrrj.smartrent.user.service.AuthService;
import com.ufrrj.smartrent.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var token = authService.authenticateUser(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        var response = LoginResponse.builder()
                .token(token)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        var user = userService.saveUser(
                registerRequest.getUsername(),
                registerRequest.getPassword()
        );

        var response = RegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();

        return ResponseEntity.status(201).body(response);
    }
}
