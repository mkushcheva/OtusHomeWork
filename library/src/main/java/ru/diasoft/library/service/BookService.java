package ru.diasoft.library.service;

import ru.diasoft.library.domain.Book;

public interface BookService {
    Book create(String title, String authorName, String genreName);
}
