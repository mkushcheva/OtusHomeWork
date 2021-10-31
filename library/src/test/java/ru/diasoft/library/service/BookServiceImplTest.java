package ru.diasoft.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.diasoft.library.dao.BookDao;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.utils.MessageSourceUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс книг должен")
@SpringBootTest
class BookServiceImplTest {
    private static final long BOOK_ID = 1;
    private static final String BOOK_TITLE_NEW = "Новая книга";

    private static final long AUTHOR_ID = 1;
    private static final String AUTHOR_NAME_NEW = "Новый Автор";
    private static final long GENRE_ID = 1;
    private static final String GENRE_NAME_NEW = "Новый Жанр";

    @Mock
    private BookDao bookDao;
    @Mock
    private AuthorService authorService;
    @Mock
    private GenreService genreService;
    @Mock
    private MessageSourceUtils messageSource;

    private BookServiceImpl bookService;

    private final Author author = new Author(AUTHOR_ID, AUTHOR_NAME_NEW);
    private final Genre genre = new Genre(GENRE_ID, GENRE_NAME_NEW);
    private final Book book = new Book(BOOK_ID, BOOK_TITLE_NEW, author, genre);

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookDao, authorService, genreService, messageSource);
    }

    @Test
    @DisplayName("Создать новую книгу")
    void shouldCreateBook() {
        when(authorService.getByName(AUTHOR_NAME_NEW)).thenReturn(author);
        when(genreService.getByName(GENRE_NAME_NEW)).thenReturn(genre);
        when(bookDao.create(BOOK_TITLE_NEW, author, genre)).thenReturn(book);

        Book bookResult = bookService.create(BOOK_TITLE_NEW, AUTHOR_NAME_NEW, GENRE_NAME_NEW);
        assertNotNull(bookResult);
        assertThat(bookResult.getTitle()).isEqualTo(BOOK_TITLE_NEW);
        assertThat(bookResult.getAuthor()).usingRecursiveComparison().isEqualTo(author);
        assertThat(bookResult.getGenre()).usingRecursiveComparison().isEqualTo(genre);

        verify(authorService, times(1)).getByName(AUTHOR_NAME_NEW);
        verify(genreService, times(1)).getByName(GENRE_NAME_NEW);
    }

    @Test
    @DisplayName("Обновить название книги")
    void shouldUpdateBook() {
        Book updateBook = new Book(BOOK_ID, "Новое название книги", author, genre);

        when(bookDao.getById(BOOK_ID)).thenReturn(java.util.Optional.of(book));
        when(authorService.getByName(AUTHOR_NAME_NEW)).thenReturn(author);
        when(genreService.getByName(GENRE_NAME_NEW)).thenReturn(genre);

        bookService.update(BOOK_ID, "Новое название книги", AUTHOR_NAME_NEW, GENRE_NAME_NEW);

        verify(authorService, times(1)).getByName(AUTHOR_NAME_NEW);
        verify(genreService, times(1)).getByName(GENRE_NAME_NEW);
        verify(bookDao, times(1)).update(updateBook);
    }

    @Test
    @DisplayName("Удалить книгу")
    void shouldDeleteBook() {
        when(bookDao.getByTitle(BOOK_TITLE_NEW)).thenReturn(java.util.Optional.of(book));

        bookService.deleteByTitle(BOOK_TITLE_NEW);
        verify(bookDao, times(1)).getByTitle(BOOK_TITLE_NEW);
        verify(bookDao, times(1)).deleteById(BOOK_ID);
    }

}