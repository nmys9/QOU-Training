package com.qoutraining.employeedirectory.model.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EmployeeRequestDTO(
        @NotBlank(message = "First name is required")
        @Size(min = 3, max = 50, message = "First Name must be between 3 and 50 characters.")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(min = 3, max = 50, message = "Last Name must be between 3 and 50 characters.")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Email format is invalid")
        String email,

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
