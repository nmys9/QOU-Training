package com.qoutraining.employeedirectory.model.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record DepartmentRequestDTO(
        @NotBlank(message = "Department name is required")
        @Size(min = 3, max = 50, message = "Department name must be between 2 and 50 characters.")
        String name
) {
}
