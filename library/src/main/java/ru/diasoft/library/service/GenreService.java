package ru.diasoft.library.service;

import ru.diasoft.library.domain.Genre;

public interface GenreService {
    Genre getByName(String name);

    void create(String name);

    void printAllGenres();

    void deleteByName(String name);
}
