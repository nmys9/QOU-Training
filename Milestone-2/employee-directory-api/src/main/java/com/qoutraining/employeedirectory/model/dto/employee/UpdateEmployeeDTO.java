package com.qoutraining.employeedirectory.model.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateEmployeeDTO(
        @NotBlank(message = "First name is required")
        @Size(min = 3, max = 50, message = "Full Name must be between 3 and 50 characters.")
        String fullName,

        @NotBlank(message = "Address is required")
        @Size(min = 3, max = 100, message = "Address must be between 3 and 100 characters.")
        String address,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "Hire date is required")
        @PastOrPresent(message = "Hire date cannot be in the future")
        LocalDate hireDate,

        @NotNull(message = "Department ID is required")
        @Positive(message = "Department ID must be a positive number.")
        Long departmentId,

        @NotNull(message = "Job Title ID is required")
        @Positive(message = "Job Title ID must be a positive number.")
        Long jobTitleId
) {
}
