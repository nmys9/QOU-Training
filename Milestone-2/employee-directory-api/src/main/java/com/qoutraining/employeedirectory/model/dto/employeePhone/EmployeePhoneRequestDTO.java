package com.qoutraining.employeedirectory.model.dto.employeePhone;

import com.qoutraining.employeedirectory.model.enums.PhoneType;
import jakarta.validation.constraints.NotNull;

public record EmployeePhoneRequestDTO(
        @NotNull(message = "Employee ID is required")
        Long employeeId,

        @NotNull(message = "Phone number is required")
        Long phoneNumber,

        @NotNull(message = "Phone type is required")
        PhoneType phoneType
) {
}