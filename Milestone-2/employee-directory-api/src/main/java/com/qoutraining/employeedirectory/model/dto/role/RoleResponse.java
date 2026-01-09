package com.qoutraining.employeedirectory.model.dto.role;

import java.time.LocalDateTime;

public record RoleResponse(
        Long id,
        String name,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate,
        String createdBy,
        String lastModifiedBy
) {
}
