package ru.diasoft.library.repository;

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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с книгами должен")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJdbcTest {
    private static final String BOOK_TITLE_NEW = "Новая книга";
    private static final long BOOK_ID_NEW = 100;
    private static final long EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "Волшебник изумрудного города";

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TestEntityManager em;

    private final Author testAuthor = new Author(3, "TestAuthor");
    private final Genre testGenre = new Genre(3, "TestGenre");
    private final List<Comment> comments = new ArrayList<>();

    @Test
    @DisplayName("добавлять книгу В БД")
    void shouldCreateBookTest() {
        int countBefore = bookRepository.getAll().size();

        Book createBook = new Book(BOOK_ID_NEW, BOOK_TITLE_NEW, testAuthor, testGenre, comments);
        bookRepository.create(createBook);

        int countAfter = bookRepository.getAll().size();

        assertNotNull(createBook);
        assertThat(createBook.getTitle()).isEqualTo(BOOK_TITLE_NEW);
        assertThat(countAfter).isGreaterThan(countBefore);
    }

    @Test
    @DisplayName("найти 9 книг в БД")
    void shouldFindAllBooks() {
        List<Book> bookList = bookRepository.getAll();
        assertThat(bookList).isNotNull().hasSize(9)
                .allMatch(b -> !b.getTitle().equals(""))
                .allMatch(b -> b.getAuthor() != null)
                .allMatch(b -> b.getGenre() != null);
    }

    @Test
    @DisplayName("возвращать книгу по идентификатору")
    void shouldReturnBookById() {
        Book findBook = bookRepository.getById(EXISTING_BOOK_ID).orElse(null);

        assertNotNull(findBook);
        assertThat(findBook.getTitle()).isEqualTo(EXISTING_BOOK_TITLE);
    }

    @Test
    @DisplayName("возвращать книгу по названию")
    void shouldReturnBookByTitle() {
        Book findBook = bookRepository.getByTitle(EXISTING_BOOK_TITLE).orElse(null);

        assertNotNull(findBook);
        assertThat(findBook.getId()).isEqualTo(EXISTING_BOOK_ID);
    }

    @Test
    @DisplayName("удалять книгу по идентификатору")
    void shouldDeleteBookById() {
        assertThatCode(() -> bookRepository.getById(EXISTING_BOOK_ID)).doesNotThrowAnyException();
        int countBefore = bookRepository.getAll().size();
        bookRepository.deleteById(EXISTING_BOOK_ID);
        int countAfter = bookRepository.getAll().size();

        assertThat(countAfter).isLessThan(countBefore);
    }

    @Test
    @DisplayName("изменить название книги")
    void shouldUpdateBookTitleTest() {
        Book oldBook = em.find(Book.class, EXISTING_BOOK_ID);
        em.detach(oldBook);

        Book newBook = new Book(EXISTING_BOOK_ID, BOOK_TITLE_NEW, oldBook.getAuthor(), oldBook.getGenre(), oldBook.getComments());

        bookRepository.update(newBook);

        Book afterUpdateBook = bookRepository.getById(EXISTING_BOOK_ID).orElse(null);

        assertNotEquals(afterUpdateBook.getTitle(), oldBook.getTitle());
        assertEquals(afterUpdateBook.getAuthor(), oldBook.getAuthor());
        assertEquals(afterUpdateBook.getGenre(), oldBook.getGenre());
        assertThat(afterUpdateBook.getTitle()).isEqualTo(BOOK_TITLE_NEW);
    }
}