package com.learn.userservice.model.dto.request;

import lombok.Builder;

@Builder
public record RegisterRequest(
    String firstName,
    String lastName,
    String email,
    String password
) { }
