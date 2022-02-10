package ru.diasoft.library.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.diasoft.library.rest.dto.BookDto;
import ru.diasoft.library.service.BookService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService bookService;

    @GetMapping("/book")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBookDto();
    }

    @GetMapping("/book/{id}")
    public BookDto getBookById(@PathVariable("id") long id) {
        return bookService.getBookDtoById(id);
    }

    @PostMapping("/book")
    public BookDto createNewBook(@RequestBody BookDto dto) {
        return bookService.createNewBookDto(dto);
    }

    @DeleteMapping("/book/{id}")
    public void deleteById(@PathVariable("id") long id) {
        bookService.deleteById(id);
    }

    @PatchMapping("/book/{id}")
    public BookDto addCommentById(@PathVariable("id") long id, @RequestBody String comment) {
        return bookService.addCommentById(id, comment);
    }
}
