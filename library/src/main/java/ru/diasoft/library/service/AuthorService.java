package ru.diasoft.library.service;

import ru.diasoft.library.domain.Author;
import ru.diasoft.library.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    Author getByName(String name);

    Author create(String name);

    void deleteById(long id);

    List<AuthorDto> getAllAuthorDto();

    AuthorDto getAuthorDtoByID(long id);

    AuthorDto createNewAuthorDto(AuthorDto dto);

}
