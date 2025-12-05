package com.qoutraining.employeedirectory.model.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AddEmployeeDTO(
        @NotEmpty(message = "First name is required")
        String firstName,

        @NotEmpty(message = "Last name is required")
        String lastName,

        @NotEmpty(message = "Email is required")
        @Email(message = "Email format is invalid")
        String email,

        @NotEmpty(message = "Address is required")
        String address,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "Hire date is required")
        LocalDate hireDate,

        @NotNull(message = "Department ID is required")
        Long departmentId,

        @NotNull(message = "Job Title ID is required")
        Long jobTitleId
) {
}
