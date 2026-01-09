package com.qoutraining.employeedirectory.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public final class ErrorResponse {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final Map<String, String> validationErrors;

}
