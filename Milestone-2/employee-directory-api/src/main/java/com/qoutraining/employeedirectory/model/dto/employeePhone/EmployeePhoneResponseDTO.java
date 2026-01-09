package com.qoutraining.employeedirectory.model.dto.employeePhone;

import com.qoutraining.employeedirectory.model.enums.PhoneType;

import java.time.LocalDateTime;

public record EmployeePhoneResponseDTO(
        Long id,
        Long phoneNumber,
        PhoneType phoneType,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate,
        String createdBy,
        String lastModifiedBy
) {
}
