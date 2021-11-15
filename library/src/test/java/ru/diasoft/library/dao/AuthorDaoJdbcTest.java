package ru.diasoft.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.diasoft.library.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {
    private static final String AUTHOR_NAME_NEW = "Новый Автор";
    private static final long EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "Пушкин А.С. test";

    @Autowired
    private AuthorDao authorDao;

    @Test
    @DisplayName("найти 3 автора в таблице БД")
    void shouldFindAllAuthor() {
        List<Author> authorList = authorDao.getAll();
        assertThat(authorList.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("добавлять автора в БД")
    void shouldCreateAuthorTest() {
        int countBefore = authorDao.getAll().size();
        Author createAuthor = authorDao.create(AUTHOR_NAME_NEW);
        int countAfter = authorDao.getAll().size();

        assertThat(createAuthor.getName()).isEqualTo(AUTHOR_NAME_NEW);
        assertThat(countAfter).isGreaterThan(countBefore);
    }

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualAuthor = authorDao.getById(expectedAuthor.getId()).orElse(null);

        assertNotNull(actualAuthor);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("удалять автора по идентификатору")
    void shouldDeleteAuthorById() {
        assertThatCode(() -> authorDao.getById(EXISTING_AUTHOR_ID)).doesNotThrowAnyException();
        int countBefore = authorDao.getAll().size();
        authorDao.deleteById(EXISTING_AUTHOR_ID);
        int countAfter = authorDao.getAll().size();

        assertThat(countAfter).isLessThan(countBefore);
    }

    @Test
    @DisplayName("возвращать автора по имени")
    void shouldReturnAuthorByName() {
        Author actualAuthor = authorDao.getByName(EXISTING_AUTHOR_NAME).orElse(null);
        assertNotNull(actualAuthor);
        assertThat(actualAuthor.getId()).isEqualTo(EXISTING_AUTHOR_ID);
    }

    @Test
    @DisplayName("обновить имя автора")
    void shouldUpdateNameAuthor() {
        Author author = new Author(EXISTING_AUTHOR_ID, AUTHOR_NAME_NEW);
        authorDao.update(author);
        Author updateAuthor = authorDao.getById(EXISTING_AUTHOR_ID).orElse(null);
        assertThat(author).usingRecursiveComparison().isEqualTo(updateAuthor);
    }
}