package com.qoutraining.employeedirectory.exception;

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
