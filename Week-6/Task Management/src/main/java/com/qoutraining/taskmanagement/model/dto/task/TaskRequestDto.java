package com.qoutraining.taskmanagement.model.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qoutraining.taskmanagement.model.enums.Priority;
import com.qoutraining.taskmanagement.model.enums.TaskStatus;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record TaskRequestDto(
        @NotNull(message = "User Id is required.")
        @Positive(message = "User Id must be positive number.")
        Long userId,

        @NotBlank(message = "Title is required.")
        @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters.")
        String title,

        @Size(min = 3, max = 1000, message = "Title must be between 3 and 1000 characters.")
        String description,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @PastOrPresent(message = "Created at cannot be in the future.")
        @NotNull
        LocalDateTime createdAt,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @FutureOrPresent(message = "Due date cannot be in the past.")
        LocalDateTime dueDate,

        @NotBlank(message = "Priority is required.")
        @Pattern(regexp = "HIGH|MEDIUM|LOW", message = "Priority must be HIGH, MEDIUM, or LOW.")
        String  priority,

        @NotBlank(message = "Task Status is required.")
        @Pattern(regexp = "PENDING|IN_PROGRESS|COMPLETED" ,message = "Task Status must be PENDING,IN_PROGRESS or COMPLETED")
        String taskStatus
) {
}
