package com.cinemahub.backend.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cinemahub.backend.dto.AuthResponse;
import com.cinemahub.backend.dto.LoginRequest;
import com.cinemahub.backend.dto.RegisterRequest;
import com.cinemahub.backend.model.User;
import com.cinemahub.backend.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        User user = authService.register(request);
        return ResponseEntity.ok(
                new AuthResponse(user.getId(), user.getEmail())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        User user = authService.login(request);
        return ResponseEntity.ok(
                new AuthResponse(user.getId(), user.getEmail())
        );
    }
}
