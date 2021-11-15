package ru.diasoft.library.service;

import ru.diasoft.library.domain.Author;

public interface AuthorService {
    Author getByName(String name);

    void create(String name);

    void printAllAuthors();

    void deleteByName(String name);
}
