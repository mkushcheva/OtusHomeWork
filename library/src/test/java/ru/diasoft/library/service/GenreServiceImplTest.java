package ru.diasoft.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.diasoft.library.repository.GenreRepository;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.utils.MessageSourceUtils;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс жанров должен")
@SpringBootTest
class GenreServiceImplTest {
    private static final long EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "test";

    @Mock
    private GenreRepository genreRepository;
    @Mock
    private MessageSourceUtils messageSource;

    private GenreServiceImpl genreService;

    @BeforeEach
    void setUp() {
        genreService = new GenreServiceImpl(genreRepository, messageSource);
    }

    @Test
    @DisplayName("Найти существующий жанр в БД")
    void shouldReturnExpectedGenreById() {
        when(genreRepository.getByName(EXISTING_GENRE_NAME))
                .thenReturn(java.util.Optional.of(new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME)));

        genreService.getByName(EXISTING_GENRE_NAME);

        verify(genreRepository, times(1)).getByName(EXISTING_GENRE_NAME);
    }

    @Test
    @DisplayName("Создать жанр в БД, которого нет ")
    void shouldCreateGenre() {
        Genre genre = new Genre(1, EXISTING_GENRE_NAME);
        when(genreRepository.create(any())).thenReturn(genre);
        genreService.create(EXISTING_GENRE_NAME);
        verify(genreRepository, times(1)).create(any());
    }
}