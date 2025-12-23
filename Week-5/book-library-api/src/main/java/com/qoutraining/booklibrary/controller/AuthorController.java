package com.qoutraining.booklibrary.controller;

import com.qoutraining.booklibrary.model.dto.author.AuthorRequestDto;
import com.qoutraining.booklibrary.model.dto.author.AuthorResponseDto;
import com.qoutraining.booklibrary.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorResponseDto> finallAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public AuthorResponseDto findAuthorById(@PathVariable Long id){
        return authorService.findById(id);
    }

    @PostMapping
    public AuthorResponseDto addAuthor(@RequestBody AuthorRequestDto dto){
        return authorService.addAuthor(dto);
    }

    @PutMapping("/{id}")
    public AuthorResponseDto updateAuthor(@PathVariable Long id, @RequestBody AuthorRequestDto dto){
        return authorService.updateAuthor(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().body("Author deleted");
    }
}
