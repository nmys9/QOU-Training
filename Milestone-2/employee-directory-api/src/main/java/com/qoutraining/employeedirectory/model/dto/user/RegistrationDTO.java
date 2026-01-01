package com.qoutraining.employeedirectory.model.dto.user;

public record RegistrationDTO(
        String fullName,
        String email,
        String password
) {
}
