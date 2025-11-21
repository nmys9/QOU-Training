package com.productcatalog.productapi.model;

import jakarta.validation.constraints.*;


public record Category(
        @Positive(message = "ID cannot by negative")
        long id,
        @NotBlank(message = "Name is required and cannot be empty")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name
) {
}
