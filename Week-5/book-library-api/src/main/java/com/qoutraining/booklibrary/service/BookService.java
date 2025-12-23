package com.qoutraining.booklibrary.service;

import com.qoutraining.booklibrary.model.dto.book.BookRequestDto;
import com.qoutraining.booklibrary.model.dto.book.BookResponseDto;
import java.util.List;

public interface BookService {
    List<BookResponseDto> findAllBooks();
    BookResponseDto findBookById(Long id);
    BookResponseDto addBook(BookRequestDto bookRequestDto);
    BookResponseDto updateBook(Long id,BookRequestDto dto);
    void deleteBook(Long id);
}
