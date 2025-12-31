package com.qoutraining.taskmanagement.model.dto.auth;

public record AuthResponse(
        String token,
        String refreshToken
) {
}
