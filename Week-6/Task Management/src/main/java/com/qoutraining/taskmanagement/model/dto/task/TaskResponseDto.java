package com.qoutraining.taskmanagement.model.dto.task;

import com.qoutraining.taskmanagement.model.enums.Priority;
import com.qoutraining.taskmanagement.model.enums.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        LocalDateTime createdAt,
        LocalDateTime dueDate,
        Priority priority,
        TaskStatus taskStatus
) {
}
