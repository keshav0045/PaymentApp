package com.project.paymentapp.controller;

import com.project.paymentapp.dto.request.LoginRequest;
import com.project.paymentapp.dto.request.RegisterRequest;
import com.project.paymentapp.dto.response.AuthResponse;
import com.project.paymentapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/test")
    public String test(Authentication auth) {
        return "Hello " + auth.getName();
    }
}
