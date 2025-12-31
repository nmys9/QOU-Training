package com.qoutraining.employeedirectory.model.dto.employee;

import com.qoutraining.employeedirectory.model.entity.EmployeeProject;
import com.qoutraining.employeedirectory.model.enums.Status;

import java.time.LocalDate;

public record EmployeeProjectResponseDTO(
        Long id,
        LocalDate startDate,
        LocalDate endDate,
        Status status,
        Long projectId,
        String projectTitle,
        String projectDescription
) {
}
