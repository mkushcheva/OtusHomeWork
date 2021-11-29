package ru.diasoft.library.service;

import ru.diasoft.library.domain.Book;

public interface BookService {
    Book create(String title, String authorName, String genreName);

    void update(Long id, String title, String authorName, String genreName);

    void deleteByTitle(String title);

    void printInfoBook(String title);

    void printAllBooks();

    void addCommentToBook(String title, String commentText);
}
