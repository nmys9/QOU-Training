package com.qoutraining.booklibrary.model.mapper;

import com.qoutraining.booklibrary.model.dto.book.BookRequestDto;
import com.qoutraining.booklibrary.model.dto.book.BookResponseDto;
import com.qoutraining.booklibrary.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    Book toEntity(BookRequestDto dto);

    BookResponseDto toResponseDto(Book entity);

    List<BookResponseDto> toResponseList(List<Book> entities);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(BookRequestDto dto,@MappingTarget Book entity);
}
