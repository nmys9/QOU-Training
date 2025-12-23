package com.qoutraining.booklibrary.service.impl;

import com.qoutraining.booklibrary.model.dto.author.AuthorRequestDto;
import com.qoutraining.booklibrary.model.dto.author.AuthorResponseDto;
import com.qoutraining.booklibrary.model.mapper.AuthorMapper;
import com.qoutraining.booklibrary.model.entity.Author;
import com.qoutraining.booklibrary.repository.AuthorRepository;
import com.qoutraining.booklibrary.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorResponseDto> findAll() {
        return authorMapper.toResponseList(authorRepository.findAll());
    }

    @Override
    public AuthorResponseDto findById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Author not found"));
        return authorMapper.toResponseDto(author);
    }

    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto dto) {
        Author author = authorMapper.toEntity(dto);
        authorRepository.save(author);
        return authorMapper.toResponseDto(author);
    }

    @Override
    public AuthorResponseDto updateAuthor(Long id, AuthorRequestDto dto) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Author not found"));
        authorMapper.updateEntityFromDto(dto, author);
        Author updateAuthor=authorRepository.save(author);
        return authorMapper.toResponseDto(updateAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Author not found"));
        authorRepository.delete(author);

    }
}
