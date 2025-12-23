package com.qoutraining.booklibrary.model.mapper;

import com.qoutraining.booklibrary.model.dto.author.AuthorRequestDto;
import com.qoutraining.booklibrary.model.dto.author.AuthorResponseDto;
import com.qoutraining.booklibrary.model.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorRequestDto dto);

    AuthorResponseDto toResponseDto(Author entity);

    List<AuthorResponseDto> toResponseList(List<Author> entities);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(AuthorRequestDto dto, @MappingTarget Author entity);

}
