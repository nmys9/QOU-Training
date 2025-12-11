package com.qoutraining.employeedirectory.model.dto.employeePhone;

import com.qoutraining.employeedirectory.model.enums.PhoneType;

public record EmployeePhoneRequestDTO(
        Long employeeId,
        Long phoneNumber,
        PhoneType phoneType
) {
}