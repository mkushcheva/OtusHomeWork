package ru.diasoft.library.rest.mapper;

import org.springframework.stereotype.Component;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Comment;
import ru.diasoft.library.rest.dto.BookDto;

import java.util.stream.Collectors;

@Component
public class BookMapperImpl implements BookMapper{
    @Override
    public Book bookDtoToBookDomain(BookDto bookDto) {
        return null;
    }

    @Override
    public BookDto bookDomainToBookDto(Book bookDomain) {
        return new BookDto(
                bookDomain.getId(),
                bookDomain.getTitle(),
                bookDomain.getAuthor().getName(),
                bookDomain.getGenre().getName(),
                bookDomain.getComments().stream().map(Comment::getCommentText).collect(Collectors.toList())
        );
    }

}
