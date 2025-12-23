package com.qoutraining.booklibrary.controller;

import com.qoutraining.booklibrary.model.dto.book.BookRequestDto;
import com.qoutraining.booklibrary.model.dto.book.BookResponseDto;
import com.qoutraining.booklibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookResponseDto> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponseDto findBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    public BookResponseDto addBook(@RequestBody BookRequestDto dto) {
        return bookService.addBook(dto);
    }

    @PutMapping("/{id}")
    public BookResponseDto updateBook(@PathVariable Long id, @RequestBody BookRequestDto dto) {
        return bookService.updateBook(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().body("Book deleted");
    }

}
