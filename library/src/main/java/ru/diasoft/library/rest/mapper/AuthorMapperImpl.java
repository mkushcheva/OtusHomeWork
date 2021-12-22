package ru.diasoft.library.rest.mapper;

import org.springframework.stereotype.Component;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.rest.dto.AuthorDto;

@Component
public class AuthorMapperImpl implements AuthorMapper {
    @Override
    public Author authorDtoToAuthorDomain(AuthorDto authorDto) {
        return new Author(authorDto.getId(), authorDto.getName());
    }

    @Override
    public AuthorDto authorDomainToAuthorDto(Author authorDomain) {
        return new AuthorDto(authorDomain.getId(), authorDomain.getName());
    }
}
