package com.qoutraining.employeedirectory.model.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        String message,
        String path,
        int status,
        LocalDateTime timestamp,
        Map<String,String> details
) {
}
