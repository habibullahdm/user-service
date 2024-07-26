package com.learn.userservice.service;

import com.learn.userservice.model.dto.request.RegisterRequest;
import com.learn.userservice.model.dto.response.RegisterResponse;
import com.learn.userservice.model.entity.User;
import com.learn.userservice.model.enums.UserType;
import com.learn.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest request) {
        var email = userRepository.findByEmail(request.email());
        if (email.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        var newUser = userRepository.save(User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(UserType.USER.name())
                .updatedBy(UserType.SYSTEM.name())
                .createdBy(UserType.SYSTEM.name())
                .build());

        return RegisterResponse.builder()
                .id(newUser.getId())
                .email(newUser.getEmail())
                .build();
    }
}
