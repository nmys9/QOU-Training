package com.qoutraining.employeedirectory.model.dto.department;

import com.qoutraining.employeedirectory.model.entity.Department;

public record DepartmentResponseDTO(
        Long id,
        String name,
        String managerName
) {
}
