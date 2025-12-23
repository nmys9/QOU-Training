package com.qoutraining.booklibrary.service;

import com.qoutraining.booklibrary.model.dto.author.AuthorRequestDto;
import com.qoutraining.booklibrary.model.dto.author.AuthorResponseDto;
import com.qoutraining.booklibrary.model.entity.Author;

import java.util.List;

public interface AuthorService {
    List<AuthorResponseDto> findAll();
    AuthorResponseDto findById(Long id);
    AuthorResponseDto addAuthor(AuthorRequestDto dto);
    AuthorResponseDto updateAuthor(Long id,AuthorRequestDto dto);
    void deleteAuthor(Long id);
}
