package com.example.demo.demo_api.model;

import jakarta.validation.constraints.*;

public record Greeting(
        @Positive(message = "ID cannot be negative")
        long id,
        @NotNull(message = "Content cannot be null")
        String content) {
}
