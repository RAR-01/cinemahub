package com.cinemahub.backend.service;

import com.cinemahub.backend.dto.LoginRequest;
import com.cinemahub.backend.dto.RegisterRequest;
import com.cinemahub.backend.model.User;

public interface AuthService {

    User register(RegisterRequest request);

    User login(LoginRequest request);
}
