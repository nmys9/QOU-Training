package com.qoutraining.employeedirectory.model.dto.department;

import com.qoutraining.employeedirectory.model.entity.Department;

import java.time.LocalDateTime;

public record DepartmentResponseDTO(
        Long id,
        String name,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate,
        String createdBy,
        String lastModifiedBy
) {
}
