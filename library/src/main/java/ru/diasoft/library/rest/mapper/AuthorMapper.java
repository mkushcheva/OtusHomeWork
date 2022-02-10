package ru.diasoft.library.rest.mapper;

import ru.diasoft.library.domain.Author;
import ru.diasoft.library.rest.dto.AuthorDto;

public interface AuthorMapper {

    Author authorDtoToAuthorDomain(AuthorDto authorDto);

    AuthorDto authorDomainToAuthorDto(Author authorDomain);
}
