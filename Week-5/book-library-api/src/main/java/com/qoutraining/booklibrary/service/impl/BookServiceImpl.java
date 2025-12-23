package com.qoutraining.booklibrary.service.impl;

import com.qoutraining.booklibrary.model.dto.book.BookRequestDto;
import com.qoutraining.booklibrary.model.dto.book.BookResponseDto;
import com.qoutraining.booklibrary.model.mapper.BookMapper;
import com.qoutraining.booklibrary.model.entity.Book;
import com.qoutraining.booklibrary.repository.BookRepository;
import com.qoutraining.booklibrary.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookResponseDto> findAllBooks() {
        return bookMapper.toResponseList(bookRepository.findAll());
    }

    @Override
    public BookResponseDto findBookById(Long id) {
        return bookMapper.toResponseDto(bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book with id " + id + " not found!")));
    }

    @Override
    @Transactional
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = bookMapper.toEntity(bookRequestDto);
        book = bookRepository.save(book);
        return bookMapper.toResponseDto(book);
    }

    @Override
    @Transactional
    public BookResponseDto updateBook(Long id, BookRequestDto dto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book with id " + id + " not found!"));
        bookMapper.updateEntityFromDto(dto,book);
        Book updateBook=bookRepository.save(book);
        return bookMapper.toResponseDto(updateBook);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book with id " + id + " not found!"));
        bookRepository.delete(book);

    }
}
