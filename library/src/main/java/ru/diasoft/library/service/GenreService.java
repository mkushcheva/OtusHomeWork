package ru.diasoft.library.service;

import ru.diasoft.library.domain.Genre;

public interface GenreService {
    Genre getByName(String name);

    Genre create(String name);
}
