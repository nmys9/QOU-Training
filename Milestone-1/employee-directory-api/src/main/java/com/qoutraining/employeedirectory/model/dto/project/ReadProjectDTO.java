package com.qoutraining.employeedirectory.model.dto.project;

import com.qoutraining.employeedirectory.model.entity.Project;
import com.qoutraining.employeedirectory.model.enums.Status;

import java.time.LocalDate;

public record ReadProjectDTO(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Status status
) {
    public static ReadProjectDTO fromEntity(Project project){
        return new ReadProjectDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus()
        );
    }
}
