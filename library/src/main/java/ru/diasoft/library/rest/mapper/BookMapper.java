package ru.diasoft.library.rest.mapper;

import ru.diasoft.library.domain.Book;
import ru.diasoft.library.rest.dto.BookDto;

public interface BookMapper {
    Book bookDtoToBookDomain(BookDto bookDto);

    BookDto bookDomainToBookDto(Book bookDomain);
}
