package com.learn.userservice.model.dto;

import lombok.Builder;

@Builder
public record BaseDataResponse<T>(T data) {
}
