package com.qoutraining.booklibrary.repository;

import com.qoutraining.booklibrary.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
