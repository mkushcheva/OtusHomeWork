package ru.diasoft.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.repository.BookRepository;
import ru.diasoft.library.rest.mapper.BookMapper;
import ru.diasoft.library.utils.MessageSourceUtils;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс книг должен")
class BookServiceImplTest {
    private static final long BOOK_ID = 1;
    private static final String BOOK_TITLE_NEW = "Новая книга";

    private static final long AUTHOR_ID = 1;
    private static final String AUTHOR_NAME_NEW = "Новый Автор";
    private static final long GENRE_ID = 1;
    private static final String GENRE_NAME_NEW = "Новый Жанр";

    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorService authorService;
    @Mock
    private GenreService genreService;
    @Mock
    private BookMapper mapper;
    @Mock
    private MessageSourceUtils messageSource;

    private BookServiceImpl bookService;

    private final Author author = new Author(AUTHOR_ID, AUTHOR_NAME_NEW);
    private final Genre genre = new Genre(GENRE_ID, GENRE_NAME_NEW);
    private final Book book = new Book(BOOK_ID, BOOK_TITLE_NEW, author, genre, new ArrayList<>(), 10);

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookRepository, authorService, genreService, mapper, messageSource);
    }

    @Test
    @DisplayName("Создать новую книгу")
    void shouldCreateBook() {
        when(authorService.getByName(AUTHOR_NAME_NEW)).thenReturn(author);
        when(genreService.getByName(GENRE_NAME_NEW)).thenReturn(genre);
        when(bookRepository.save(any())).thenReturn(book);

        Book bookResult = bookService.create(BOOK_TITLE_NEW, AUTHOR_NAME_NEW, GENRE_NAME_NEW);
        assertNotNull(bookResult);
        assertThat(bookResult.getTitle()).isEqualTo(BOOK_TITLE_NEW);
        assertThat(bookResult.getAuthor()).usingRecursiveComparison().isEqualTo(author);
        assertThat(bookResult.getGenre()).usingRecursiveComparison().isEqualTo(genre);

        verify(authorService, times(1)).getByName(AUTHOR_NAME_NEW);
        verify(genreService, times(1)).getByName(GENRE_NAME_NEW);
    }
}