package com.qoutraining.employeedirectory.model.dto.employeePhone;

import com.qoutraining.employeedirectory.model.enums.PhoneType;

public record EmployeePhoneResponseDTO(
        Long id,
        Long phoneNumber,
        PhoneType phoneType
) {
}
