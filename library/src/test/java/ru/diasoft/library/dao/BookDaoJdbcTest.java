package ru.diasoft.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {
    private static final String BOOK_TITLE_NEW = "Новая книга";
    private static final long EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "Волшебник изумрудного города";

    @Autowired
    private BookDao bookDao;

    private final Author testAuthor = new Author(3, "TestAuthor");
    private final Genre testGenre = new Genre(3, "TestGenre");

    @Test
    @DisplayName("добавлять книгу В БД")
    void shouldCreateBookTest() {
        int countBefore = bookDao.getAll().size();
        Book createBook = bookDao.create(BOOK_TITLE_NEW, testAuthor, testGenre);
        int countAfter = bookDao.getAll().size();

        assertNotNull(createBook);
        assertThat(createBook.getTitle()).isEqualTo(BOOK_TITLE_NEW);
        assertThat(countAfter).isGreaterThan(countBefore);
    }

    @Test
    @DisplayName("найти 9 книг в БД")
    void shouldFindAllBooks() {
        List<Book> bookList = bookDao.getAll();
        assertThat(bookList.size()).isEqualTo(9);
    }

    @Test
    @DisplayName("возвращать книгу по идентификатору")
    void shouldReturnBookById() {
        Book findBook = bookDao.getById(EXISTING_BOOK_ID).orElse(null);

        assertNotNull(findBook);
        assertThat(findBook.getTitle()).isEqualTo(EXISTING_BOOK_TITLE);
    }

    @Test
    @DisplayName("возвращать книгу по названию")
    void shouldReturnBookByTitle() {
        Book findBook = bookDao.getByTitle(EXISTING_BOOK_TITLE).orElse(null);

        assertNotNull(findBook);
        assertThat(findBook.getId()).isEqualTo(EXISTING_BOOK_ID);
    }

    @Test
    @DisplayName("удалять книгу по идентификатору")
    void shouldDeleteBookById() {
        assertThatCode(() -> bookDao.getById(EXISTING_BOOK_ID)).doesNotThrowAnyException();
        int countBefore = bookDao.getAll().size();
        bookDao.deleteById(EXISTING_BOOK_ID);
        int countAfter = bookDao.getAll().size();

        assertThat(countAfter).isLessThan(countBefore);
    }

    @Test
    @DisplayName("изменить название книги")
    void shouldUpdateBookTitleTest() {
        Book oldBook = bookDao.getById(EXISTING_BOOK_ID).orElse(null);
        Book newBook = new Book(EXISTING_BOOK_ID, BOOK_TITLE_NEW, oldBook.getAuthor(), oldBook.getGenre());

        bookDao.update(newBook);

        Book afterUpdateBook = bookDao.getById(EXISTING_BOOK_ID).orElse(null);

        assertNotEquals(afterUpdateBook.getTitle(), oldBook.getTitle());
        assertEquals(afterUpdateBook.getAuthor(), oldBook.getAuthor());
        assertEquals(afterUpdateBook.getGenre(), oldBook.getGenre());
        assertThat(afterUpdateBook.getTitle()).isEqualTo(BOOK_TITLE_NEW);
    }
}