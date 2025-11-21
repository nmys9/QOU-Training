package com.productcatalog.productapi.dto;

public record ProductResponse(
    long id,
    String title,
    String description,
    double price,
    String categoryName
) {
}
