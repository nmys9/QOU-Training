package com.qoutraining.booklibrary.model.dto.author;

import java.time.LocalDate;

public record AuthorResponseDto(
        Long id,
        String name,
        String email,
        String address,
        LocalDate birthDate
) {
}
