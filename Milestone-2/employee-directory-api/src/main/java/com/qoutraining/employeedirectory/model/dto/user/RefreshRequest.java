package com.qoutraining.employeedirectory.model.dto.user;

import jakarta.validation.constraints.NotBlank;

public record RefreshRequest(
        @NotBlank(message = "Refresh token is required")
        String refreshToken
) {
}
