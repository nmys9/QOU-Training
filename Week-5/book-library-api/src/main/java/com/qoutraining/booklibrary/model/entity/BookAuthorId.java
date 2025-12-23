package com.qoutraining.booklibrary.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BookAuthorId implements Serializable
{
    @Column(name = "AUTHOR_ID")
    private Long authorId;

    @Column(name = "BOOK_ID")
    private Long bookId;
}
