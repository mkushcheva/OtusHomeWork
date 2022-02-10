package ru.diasoft.library.service;

import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.rest.dto.GenreDto;

import java.util.List;

public interface GenreService {
    Genre getByName(String name);

    Genre create(String name);

    void deleteById(long id);

    List<GenreDto> getAllGenreDto();

    GenreDto getGenreDtoById(long id);

    GenreDto createNewGenreDto(GenreDto dto);
}
