package ru.diasoft.library.rest.mapper;

import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.rest.dto.GenreDto;

public interface GenreMapper {
    Genre genreDtoToGenreDomain(GenreDto genreDto);

    GenreDto genreDomainToGenreDto(Genre authorDomain);
}
