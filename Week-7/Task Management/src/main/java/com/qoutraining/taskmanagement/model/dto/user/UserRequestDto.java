package com.qoutraining.taskmanagement.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UserRequestDto(
        @NotBlank(message = "Full Name is required")
        @Size(min = 3, max = 100, message = "Full Name must be between 3 and 100 characters.")
        String fullName,

        @NotBlank(message = "Email is required")
        @Email(message = "Email format is invalid")
        @Size(min = 3, max = 100, message = "Email must be between 3 and 100 characters.")
        String email,

        @NotBlank(message = "Password is required")
        String password


) {
}
