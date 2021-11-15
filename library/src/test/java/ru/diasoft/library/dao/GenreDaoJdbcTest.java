package ru.diasoft.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.diasoft.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {
    private static final String GENRE_NAME_NEW = "Новый Жанр";
    private static final long EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "Сказка";

    @Autowired
    private GenreDao genreDao;

    @Test
    @DisplayName("найти 3 жанра в таблице БД")
    void shouldFindAllGenre() {
        List<Genre> GenreList = genreDao.getAll();
        assertThat(GenreList.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("добавить жанр в БД")
    void shouldCreateGenreTest() {
        int countBefore = genreDao.getAll().size();
        Genre createGenre = genreDao.create(GENRE_NAME_NEW);
        int countAfter = genreDao.getAll().size();

        assertThat(createGenre.getName()).isEqualTo(GENRE_NAME_NEW);
        assertThat(countAfter).isGreaterThan(countBefore);
    }

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualGenre = genreDao.getById(expectedGenre.getId()).orElse(null);

        assertNotNull(actualGenre);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("удалять жанр по идентификатору")
    void shouldDeleteGenreById() {
        assertThatCode(() -> genreDao.getById(EXISTING_GENRE_ID)).doesNotThrowAnyException();
        int countBefore = genreDao.getAll().size();
        genreDao.deleteById(EXISTING_GENRE_ID);
        int countAfter = genreDao.getAll().size();

        assertThat(countAfter).isLessThan(countBefore);
    }

    @Test
    @DisplayName("возвращать жанр по названию")
    void shouldReturnGenreByName() {
        Genre actualGenre = genreDao.getByName(EXISTING_GENRE_NAME).orElse(null);
        assertNotNull(actualGenre);
        assertThat(actualGenre.getId()).isEqualTo(EXISTING_GENRE_ID);
    }

    @Test
    @DisplayName("обновить название жанра")
    void shouldUpdateNameGenre() {
        Genre genre = new Genre(EXISTING_GENRE_ID, GENRE_NAME_NEW);
        genreDao.update(genre);
        Genre updateGenre = genreDao.getById(EXISTING_GENRE_ID).orElse(null);
        assertThat(genre).usingRecursiveComparison().isEqualTo(updateGenre);
    }
}