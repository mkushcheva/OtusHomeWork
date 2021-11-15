package ru.diasoft.library.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.diasoft.library.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Репозиторий для работы с авторами должен")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {
    private static final String AUTHOR_NAME_NEW = "Новый Автор";
    private static final long EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "Пушкин А.С. test";

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("найти 3 автора в таблице БД")
    void shouldFindAllAuthor() {
        List<Author> authorList = authorRepository.getAll();
        assertThat(authorList.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("добавлять автора в БД")
    void shouldCreateAuthorTest() {
        int countBefore = authorRepository.getAll().size();
        Author newAuthor = new Author(0L,AUTHOR_NAME_NEW);
        Author createAuthor = authorRepository.create(newAuthor);

        int countAfter = authorRepository.getAll().size();

        assertThat(createAuthor.getName()).isEqualTo(AUTHOR_NAME_NEW);
        assertThat(countAfter).isGreaterThan(countBefore);
    }

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualAuthor = authorRepository.getById(expectedAuthor.getId()).orElse(null);

        assertNotNull(actualAuthor);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("удалять автора по идентификатору")
    void shouldDeleteAuthorById() {
        assertThatCode(() -> authorRepository.getById(EXISTING_AUTHOR_ID)).doesNotThrowAnyException();
        int countBefore = authorRepository.getAll().size();
        authorRepository.deleteById(EXISTING_AUTHOR_ID);
        int countAfter = authorRepository.getAll().size();

        assertThat(countAfter).isLessThan(countBefore);
    }

    @Test
    @DisplayName("возвращать автора по имени")
    void shouldReturnAuthorByName() {
        Author actualAuthor = authorRepository.getByName(EXISTING_AUTHOR_NAME).orElse(null);
        assertNotNull(actualAuthor);
        assertThat(actualAuthor.getId()).isEqualTo(EXISTING_AUTHOR_ID);
    }

    @Test
    @DisplayName("обновить имя автора")
    void shouldUpdateNameAuthor() {
        Author author = new Author(EXISTING_AUTHOR_ID, AUTHOR_NAME_NEW);
        authorRepository.update(author);
        Author updateAuthor = authorRepository.getById(EXISTING_AUTHOR_ID).orElse(null);
        assertThat(author).usingRecursiveComparison().isEqualTo(updateAuthor);
    }
}