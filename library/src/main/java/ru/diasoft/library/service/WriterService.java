package ru.diasoft.library.service;

import org.springframework.lang.Nullable;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Genre;

import java.util.List;

public interface WriterService {
    void printMessage(String msg);
    void printMessage(String msg, @Nullable Object[] args);

    void printAllAuthors(List<Author> authors);
    void printAllGenres(List<Genre> genres);

    void printInfoBook(Book book);
    void printAllBooks(List<Book> books);
}
