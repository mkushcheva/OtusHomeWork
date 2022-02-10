package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Comment;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.exception.NotFoundException;
import ru.diasoft.library.repository.BookRepository;
import ru.diasoft.library.rest.dto.BookDto;
import ru.diasoft.library.rest.mapper.BookMapper;
import ru.diasoft.library.utils.MessageSourceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookMapper mapper;
    private final MessageSourceUtils messageSource;

    @Override
    @Transactional
    public Book create(String title, String authorName, String genreName) {
        //Изначально книга добавляется с пустым комментарием. комментировать книгу можно после ее прочтения.
        return bookRepository.save(
                new Book(
                        0,
                        title,
                        getAuthor(authorName),
                        getGenre(genreName),
                        new ArrayList<>())
        );
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getAllBookDto() {
        return bookRepository.findAll().stream()
                .map(mapper::bookDomainToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getBookDtoById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageSource.getMessage("book.notFound")));
        return mapper.bookDomainToBookDto(book);
    }

    @Override
    @Transactional
    public BookDto createNewBookDto(BookDto dto) {
        Book book = create(dto.getTitle(), dto.getAuthorName(), dto.getGenreName());
        return mapper.bookDomainToBookDto(book);
    }

    @Override
    public BookDto addCommentById(long id, String comment) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageSource.getMessage("book.notFound")));

        book.getComments().add(new Comment(0, comment));
        return mapper.bookDomainToBookDto(bookRepository.save(book));
    }

    private Author getAuthor(String authorName) {
        Author author = authorService.getByName(authorName);
        if (author != null) return author;
        return authorService.create(authorName);
    }

    private Genre getGenre(String genreName) {
        Genre genre = genreService.getByName(genreName);
        if (genre != null) return genre;
        return genreService.create(genreName);
    }
}
