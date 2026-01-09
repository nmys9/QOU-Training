package com.qoutraining.employeedirectory.model.dto.jobTitle;

import com.qoutraining.employeedirectory.model.entity.JobTitle;

import java.time.LocalDateTime;

public record JobTitleResponseDTO(
        Long id,
        String name,
        String description,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate,
        String createdBy,
        String lastModifiedBy
) {
}
