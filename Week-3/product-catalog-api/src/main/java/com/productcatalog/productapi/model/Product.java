package com.productcatalog.productapi.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record Product(
        @Positive(message = "ID cannot by negative")
        Long id,
        @NotBlank(message = "Title is required and cannot be empty")
        @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
        String title,
        @NotBlank(message = "Description is required and cannot be empty")
        @Size(min = 3, max = 500, message = "Description must be between 3 and 500 characters")
        String description,
        @Min(value = 1, message = "Price must be at least 1")
        Double price,
        @Valid()
        @NotNull(message = "Category details are required")
        Category category
) {
}
