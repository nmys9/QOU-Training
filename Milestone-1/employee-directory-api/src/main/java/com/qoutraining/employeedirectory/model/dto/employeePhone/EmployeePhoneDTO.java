package com.qoutraining.employeedirectory.model.dto.employeePhone;

import com.qoutraining.employeedirectory.model.enums.PhoneType;

public record EmployeePhoneDTO(
        Long phoneNumber,
        PhoneType phoneType
) {
}