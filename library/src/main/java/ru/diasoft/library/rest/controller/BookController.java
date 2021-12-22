package ru.diasoft.library.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Comment;
import ru.diasoft.library.exception.NotFoundException;
import ru.diasoft.library.repository.BookRepositoryJpa;
import ru.diasoft.library.rest.dto.BookDto;
import ru.diasoft.library.rest.mapper.BookMapper;
import ru.diasoft.library.service.BookService;
import ru.diasoft.library.utils.MessageSourceUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookRepositoryJpa repository;
    private final BookMapper mapper;
    private final MessageSourceUtils messageSource;
    private final BookService bookService;

    @GetMapping("/book")
    public List<BookDto> getAllBooks() {
        return repository.findAll().stream()
                .map(mapper::bookDomainToBookDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/book/{id}")
    public BookDto getBookById(@PathVariable("id") long id) {
        Book book = repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageSource.getMessage("book.notFound")));
        return mapper.bookDomainToBookDto(book);
    }

    @PostMapping("/book")
    public BookDto createNewBook(@RequestBody BookDto dto) {
        Book book = bookService.create(dto.getTitle(), dto.getAuthorName(), dto.getGenreName());
        return mapper.bookDomainToBookDto(book);
    }

    @DeleteMapping("/book/{id}")
    public void deleteById(@PathVariable("id") long id) {
        repository.deleteById(id);
    }

    @PatchMapping("/book/{id}")
    public BookDto addCommentById(@PathVariable("id") long id, @RequestBody String comment) {
        Book book = repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageSource.getMessage("book.notFound")));

        book.getComments().add(new Comment(0, comment));
        return mapper.bookDomainToBookDto(book);
    }
}
