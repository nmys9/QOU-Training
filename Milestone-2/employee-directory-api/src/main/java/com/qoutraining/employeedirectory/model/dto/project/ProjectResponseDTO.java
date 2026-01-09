package com.qoutraining.employeedirectory.model.dto.project;

import com.qoutraining.employeedirectory.model.entity.Project;
import com.qoutraining.employeedirectory.model.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProjectResponseDTO(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Status status,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate,
        String createdBy,
        String lastModifiedBy
) {
}
