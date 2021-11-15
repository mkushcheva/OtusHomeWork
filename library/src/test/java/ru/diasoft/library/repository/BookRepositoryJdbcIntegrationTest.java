package ru.diasoft.library.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Comment;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.service.BookService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Интеграционный тест должен")
@DataJpaTest
@Import({BookRepositoryJpa.class, AuthorRepositoryJpa.class, GenreRepositoryJpa.class})
public class BookRepositoryJdbcIntegrationTest {
    private static final String AUTHOR_NAME_NEW = "Интеграционный Автор";
    private static final String GENRE_NAME_NEW = "Интеграционный Жанр";
    private static final String BOOK_TITLE_NEW = "Интеграционная книга";

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @BeforeEach
    void fillBase() {
        Author testAuthor = new Author(3, AUTHOR_NAME_NEW);
        Genre testGenre = new Genre(3, GENRE_NAME_NEW);
        List<Comment> comments = new ArrayList<>();
        Book book = new Book(100, BOOK_TITLE_NEW, testAuthor, testGenre, comments);


        Author createAuthor = authorRepository.create(testAuthor);
        Genre createGenre = genreRepository.create(testGenre);
        Book createBook = bookRepository.create(book);

        assertNotNull(createAuthor);
        assertNotNull(createGenre);
        assertNotNull(createBook);

        //У книги правильно заполнились жанр и автор
        assertEquals(createBook.getAuthor(), createAuthor);
        assertEquals(createBook.getGenre(), createGenre);
    }

    @Test
    @DisplayName("удалить книгу при удалении жанра")
    void shouldDeleteBookWhenDeletingItsGenreTest() {
        Book book = bookRepository.getByTitle(BOOK_TITLE_NEW).orElse(null);
        assertNotNull(book);
        em.detach(book);
        Genre genre = genreRepository.getById(book.getGenre().getId()).orElse(null);
        assertNotNull(genre);
        em.detach(genre);

        //Удалим жанр и должен удалится книга
        genreRepository.deleteById(book.getGenre().getId());
        Genre genreAfterDel = genreRepository.getById(book.getGenre().getId()).orElse(null);
        assertNull(genreAfterDel);

        Book bookAfterDel = bookRepository.getByTitle(BOOK_TITLE_NEW).orElse(null);
        assertNull(bookAfterDel);
    }

    @Test
    @DisplayName("удалить книгу при удалении ее автора")
    void shouldDeleteBookWhenDeletingItsAuthorTest() {
        Book book = bookRepository.getByTitle(BOOK_TITLE_NEW).orElse(null);
        assertNotNull(book);
        em.detach(book);
        Author author = authorRepository.getById(book.getAuthor().getId()).orElse(null);
        assertNotNull(author);
        em.detach(author);

        //Удалим автора и должен удалится книга
        authorRepository.deleteById(book.getAuthor().getId());
        Author authorAfterDel = authorRepository.getById(book.getAuthor().getId()).orElse(null);
        assertNull(authorAfterDel);

        Book bookAfterDel = bookRepository.getByTitle(BOOK_TITLE_NEW).orElse(null);
        assertNull(bookAfterDel);
    }
}
