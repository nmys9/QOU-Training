package com.qoutraining.booklibrary.model.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record BookRequestDto(
        @NotBlank(message = "Book Title is required")
        @Size(min = 3, max = 50, message = "Book Title must be between 3 and 50 characters.")
        String title,

        @Size(min = 10, max = 200, message = "Description must be between 10 and 200 characters, if provided.")
        String description,

        @NotBlank(message = "ISBN is required")
        String ISBN,

        Long numberOfPages,
        LocalDate publishDate,
        List<Long> authorsId
) {
}
