package com.project.paymentapp.service;

import com.project.paymentapp.dto.request.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
}
