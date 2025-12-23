package com.qoutraining.booklibrary.model.dto.book;

import java.time.LocalDate;

public record BookResponseDto(
        Long id,
        String title,
        String description,
        String ISBN,
        Long numberOfPages,
        LocalDate publishDate
) {
}
