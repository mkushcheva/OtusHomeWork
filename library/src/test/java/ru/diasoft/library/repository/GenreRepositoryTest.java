package ru.diasoft.library.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.diasoft.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Репозиторий для работы с жанрами должен")
@DataJpaTest
class GenreRepositoryTest {
    private static final String GENRE_NAME_NEW = "Новый Жанр";
    private static final long EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "Сказка";

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("найти 3 жанра в таблице БД")
    void shouldFindAllGenre() {
        List<Genre> GenreList = genreRepository.findAll();
        assertThat(GenreList.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("добавить жанр в БД")
    void shouldCreateGenreTest() {
        int countBefore = genreRepository.findAll().size();
        Genre newGenre = new Genre(0L, GENRE_NAME_NEW);
        Genre createGenre = genreRepository.save(newGenre);
        int countAfter = genreRepository.findAll().size();

        assertThat(createGenre.getName()).isEqualTo(GENRE_NAME_NEW);
        assertThat(countAfter).isGreaterThan(countBefore);
    }

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualGenre = genreRepository.findById(expectedGenre.getId()).orElse(null);

        assertNotNull(actualGenre);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("удалять жанр по идентификатору")
    void shouldDeleteGenreById() {
        assertThatCode(() -> genreRepository.findById(EXISTING_GENRE_ID)).doesNotThrowAnyException();
        int countBefore = genreRepository.findAll().size();
        genreRepository.deleteById(EXISTING_GENRE_ID);
        int countAfter = genreRepository.findAll().size();

        assertThat(countAfter).isLessThan(countBefore);
    }

    @Test
    @DisplayName("возвращать жанр по названию")
    void shouldReturnGenreByName() {
        Genre actualGenre = genreRepository.findByName(EXISTING_GENRE_NAME).orElse(null);
        assertNotNull(actualGenre);
        assertThat(actualGenre.getId()).isEqualTo(EXISTING_GENRE_ID);
    }

    @Test
    @DisplayName("обновить название жанра")
    void shouldUpdateNameGenre() {
        Genre genre = new Genre(EXISTING_GENRE_ID, GENRE_NAME_NEW);
        genreRepository.save(genre);
        Genre updateGenre = genreRepository.findById(EXISTING_GENRE_ID).orElse(null);
        assertThat(genre).usingRecursiveComparison().isEqualTo(updateGenre);
    }
}