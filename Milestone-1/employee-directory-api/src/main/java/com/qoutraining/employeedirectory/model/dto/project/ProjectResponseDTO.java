package com.qoutraining.employeedirectory.model.dto.project;

import com.qoutraining.employeedirectory.model.entity.Project;
import com.qoutraining.employeedirectory.model.enums.Status;

import java.time.LocalDate;

public record ProjectResponseDTO(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Status status
) {
}
