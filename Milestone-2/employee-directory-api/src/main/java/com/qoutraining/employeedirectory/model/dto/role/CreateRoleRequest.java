package com.qoutraining.employeedirectory.model.dto.role;

import jakarta.validation.constraints.NotBlank;

public record CreateRoleRequest(
        @NotBlank(message = "Role name is required.")
        String name
) {
}
