package ru.diasoft.library.service;

import ru.diasoft.library.domain.Book;
import ru.diasoft.library.rest.dto.BookDto;

import java.util.List;

public interface BookService {
    Book create(String title, String authorName, String genreName);

    void deleteById(long id);

    List<BookDto> getAllBookDto();

    BookDto getBookDtoById(long id);

    BookDto createNewBookDto(BookDto dto);

    BookDto addCommentById(long id, String comment);
}
