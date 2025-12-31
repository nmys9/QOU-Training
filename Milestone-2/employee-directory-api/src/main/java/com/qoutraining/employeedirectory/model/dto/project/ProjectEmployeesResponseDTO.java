package com.qoutraining.employeedirectory.model.dto.project;

import com.qoutraining.employeedirectory.model.entity.EmployeeProject;
import com.qoutraining.employeedirectory.model.enums.Status;

import java.time.LocalDate;

public record ProjectEmployeesResponseDTO(
        Long id,
        String employeeName,
        LocalDate startDate,
        LocalDate endDate,
        Status status
) {
}
