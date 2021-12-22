package ru.diasoft.library.service;

import ru.diasoft.library.domain.Author;

public interface AuthorService {
    Author getByName(String name);

    Author create(String name);
}
