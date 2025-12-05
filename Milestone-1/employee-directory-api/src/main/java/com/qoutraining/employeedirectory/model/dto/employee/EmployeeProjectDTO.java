package com.qoutraining.employeedirectory.model.dto.employee;

import com.qoutraining.employeedirectory.model.entity.EmployeeProject;
import com.qoutraining.employeedirectory.model.enums.Status;

import java.time.LocalDate;

public record EmployeeProjectDTO(
        Long id,
        LocalDate startDate,
        LocalDate endDate,
        Status status,
        Long projectId,
        String projectTitle,
        String projectDescription
) {
    public static EmployeeProjectDTO fromEntity(EmployeeProject ep){
        return new EmployeeProjectDTO(
                ep.getId(),
                ep.getStartDate(),
                ep.getEndDate(),
                ep.getStatus(),
                ep.getProject().getId(),
                ep.getProject().getTitle(),
                ep.getProject().getDescription()
        );
    }
}
