package com.qoutraining.booklibrary.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUTHOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<BookAuthor> bookAuthorList;

}
