package com.productcatalog.productapi.dto;

public record ProductResponse(
    Long id,
    String title,
    String description,
    Double price,
    String categoryName
) {
}
