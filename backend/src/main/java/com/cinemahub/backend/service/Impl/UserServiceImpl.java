package com.cinemahub.backend.service.Impl;

import org.springframework.stereotype.Service;

import com.cinemahub.backend.dto.UserRequestDTO;
import com.cinemahub.backend.dto.UserResponseDTO;
import com.cinemahub.backend.model.User;
import com.cinemahub.backend.repository.UserRepository;
import com.cinemahub.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO request) {

        // 1. Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // 2. Map DTO -> Entity
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // hashing later

        // 3. Save user
        User savedUser = userRepository.save(user);

        // 4. Map Entity -> Response DTO
        UserResponseDTO response = new UserResponseDTO();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());

        return response;
    }
}
