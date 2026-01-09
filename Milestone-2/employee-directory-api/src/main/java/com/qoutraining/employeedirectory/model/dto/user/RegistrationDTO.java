package com.qoutraining.employeedirectory.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationDTO(
        @NotBlank(message = "Full name is required")
        @Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters")
        String fullName,

        @NotBlank(message = "Email is required")
        @Email(message = "Please provide a valid email address")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password
) {
}
