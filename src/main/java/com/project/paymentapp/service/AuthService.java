package com.project.paymentapp.service;

import com.project.paymentapp.dto.request.LoginRequest;
import com.project.paymentapp.dto.request.RegisterRequest;
import com.project.paymentapp.dto.response.AuthResponse;

public interface AuthService {
    String register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
