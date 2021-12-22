package ru.diasoft.library.rest.mapper;

import org.springframework.stereotype.Component;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.rest.dto.GenreDto;

@Component
public class GenreMapperImpl implements GenreMapper {
    @Override
    public Genre genreDtoToGenreDomain(GenreDto genreDto) {
        return new Genre(genreDto.getId(), genreDto.getName());
    }

    @Override
    public GenreDto genreDomainToGenreDto(Genre authorDomain) {
        return new GenreDto(authorDomain.getId(), authorDomain.getName());
    }
}
