package com.project.paymentapp.service.impl;

import com.project.paymentapp.dto.request.LoginRequest;
import com.project.paymentapp.dto.request.RegisterRequest;
import com.project.paymentapp.dto.response.AuthResponse;
import com.project.paymentapp.entity.User;
import com.project.paymentapp.enums.Role;
import com.project.paymentapp.repository.UserRepository;
import com.project.paymentapp.security.JwtUtil;
import com.project.paymentapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String register(RegisterRequest request) {

        // Check if user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // Create user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // 🔥 hash password
                .phoneNumber(request.getPhoneNumber())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}
