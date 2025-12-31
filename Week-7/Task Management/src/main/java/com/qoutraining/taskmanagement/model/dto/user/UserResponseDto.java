package com.qoutraining.taskmanagement.model.dto.user;

public record UserResponseDto(
        Long id,
        String fullName,
        String email
) {
}
