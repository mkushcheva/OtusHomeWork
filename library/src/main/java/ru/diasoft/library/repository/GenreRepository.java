package ru.diasoft.library.repository;

import ru.diasoft.library.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre create(Genre genre);

    void deleteById(long id);

    void update(Genre genre);

    Optional<Genre> getById(long id);

    Optional<Genre> getByName(String name);

    List<Genre> getAll();
}
