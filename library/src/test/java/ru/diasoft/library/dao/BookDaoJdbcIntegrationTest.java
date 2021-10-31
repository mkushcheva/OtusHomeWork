package ru.diasoft.library.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Genre;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Интеграционный тест должен")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
public class BookDaoJdbcIntegrationTest {
    private static final String AUTHOR_NAME_NEW = "Интеграционный Автор";
    private static final String GENRE_NAME_NEW = "Интеграционный Жанр";
    private static final String BOOK_TITLE_NEW = "Интеграционная книга";

    @Autowired
    private GenreDao genreDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private BookDao bookDao;

    @BeforeEach
    void fillBase() {
        Author createAuthor = authorDao.create(AUTHOR_NAME_NEW);
        Genre createGenre = genreDao.create(GENRE_NAME_NEW);
        Book createBook = bookDao.create(BOOK_TITLE_NEW, createAuthor, createGenre);

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
        Book book = bookDao.getByTitle(BOOK_TITLE_NEW).orElse(null);
        assertNotNull(book);
        Genre genre = genreDao.getById(book.getGenre().getId()).orElse(null);
        assertNotNull(genre);

        //Удалим жанр и должен удалится книга
        genreDao.deleteById(book.getGenre().getId());
        Genre genreAfterDel = genreDao.getById(book.getGenre().getId()).orElse(null);
        assertNull(genreAfterDel);

        Book bookAfterDel = bookDao.getByTitle(BOOK_TITLE_NEW).orElse(null);
        assertNull(bookAfterDel);
    }

    @Test
    @DisplayName("удалить книгу при удалении ее автора")
    void shouldDeleteBookWhenDeletingItsAuthorTest() {
        Book book = bookDao.getByTitle(BOOK_TITLE_NEW).orElse(null);
        assertNotNull(book);
        Author author = authorDao.getById(book.getAuthor().getId()).orElse(null);
        assertNotNull(author);

        //Удалим автора и должен удалится книга
        authorDao.deleteById(book.getAuthor().getId());
        Author authorAfterDel = authorDao.getById(book.getAuthor().getId()).orElse(null);
        assertNull(authorAfterDel);

        Book bookAfterDel = bookDao.getByTitle(BOOK_TITLE_NEW).orElse(null);
        assertNull(bookAfterDel);
    }
}
