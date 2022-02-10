package ru.diasoft.library.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Comment;
import ru.diasoft.library.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Интеграционный тест должен")
@DataJpaTest
class BookRepositoryJdbcIntegrationTest {
    private static final String AUTHOR_NAME_NEW = "Интеграционный Автор";
    private static final String GENRE_NAME_NEW = "Интеграционный Жанр";
    private static final String BOOK_TITLE_NEW = "Интеграционная книга";

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void fillBase() {
        Author testAuthor = new Author(3, AUTHOR_NAME_NEW);
        Genre testGenre = new Genre(3, GENRE_NAME_NEW);
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1l, "Comment 1"));
        comments.add(new Comment(2l, "Comment 2"));
        Book book = new Book(100, BOOK_TITLE_NEW, testAuthor, testGenre, comments);

        Author createAuthor = authorRepository.save(testAuthor);
        Genre createGenre = genreRepository.save(testGenre);
        Book createBook = bookRepository.save(book);

        assertNotNull(createAuthor);
        assertNotNull(createGenre);
        assertNotNull(createBook);

        //У книги правильно заполнились жанр и автор
        assertEquals(createBook.getAuthor(), createAuthor);
        assertEquals(createBook.getGenre(), createGenre);
    }

    @Test
    @DisplayName("удалить комментарии при удалении книги")
    void shouldDeleteCommentsWhenDeletingItsBookTest() {
        Book book = bookRepository.findByTitle(BOOK_TITLE_NEW).orElse(null);
        assertNotNull(book);

        assertThat(authorRepository.findAll().size()).isEqualTo(3);
        assertThat(genreRepository.findAll().size()).isEqualTo(3);


        //Удалим книгу и должны удалиться все ее комментарии
        bookRepository.deleteById(book.getId());
        assertThat(commentRepository.findAll().size()).isEqualTo(2);

        //Авторы и жанры должны остаться
        assertThat(authorRepository.findAll().size()).isEqualTo(3);
        assertThat(genreRepository.findAll().size()).isEqualTo(3);
    }
}
