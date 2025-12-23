package com.qoutraining.booklibrary.model.dto.author;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorRequestDto(
        @NotBlank(message = "Author Name is required")
        @Size(min = 3, max = 50, message = "Author name must be between 3 and 50 characters.")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email format is invalid")
        String email,

        @NotBlank(message = "Address is required")
        @Size(min = 3, max = 100, message = "Address must be between 3 and 100 characters.")
        String address,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotBlank(message = "Birth date is required")
        @PastOrPresent(message = "Birth date cannot be in the future")
        LocalDate birthDate
) {
}
