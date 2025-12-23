package com.qoutraining.booklibrary.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ISBN", unique = true,nullable = false)
    private String ISBN;

    @Column(name = "NUMBEROFPAGES", nullable = false)
    private Long numberOfPages;

    @Column(name = "PUBLISHDATE", nullable = false)
    private LocalDate publishDate;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookAuthor> bookAuthorList;

}
