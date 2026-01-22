package com.cinemahub.backend.service;

import com.cinemahub.backend.dto.UserRequestDTO;
import com.cinemahub.backend.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO registerUser(UserRequestDTO request);
}

