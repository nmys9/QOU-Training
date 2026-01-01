package com.qoutraining.employeedirectory.model.dto.user;

public record AuthResponse(
        String accessToken ,
        String refreshToken
) {
}
