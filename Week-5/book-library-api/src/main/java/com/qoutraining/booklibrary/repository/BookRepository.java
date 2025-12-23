package com.qoutraining.booklibrary.repository;

import com.qoutraining.booklibrary.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
