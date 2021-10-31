package ru.diasoft.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.diasoft.library.dao.AuthorDao;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.utils.MessageSourceUtils;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс авторов должен")
@SpringBootTest
class AuthorServiceImplTest {
    private static final long EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "test";

    @Mock
    private AuthorDao authorDao;
    @Mock
    private MessageSourceUtils messageSource;

    private AuthorServiceImpl authorService;

    @BeforeEach
    void setUp() {
        authorService = new AuthorServiceImpl(authorDao, messageSource);
    }

    @Test
    @DisplayName("Найти существующего автора в БД")
    void shouldReturnExpectedAuthorById() {
        when(authorDao.getByName(EXISTING_AUTHOR_NAME))
                .thenReturn(java.util.Optional.of(new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME)));

        authorService.getByName(EXISTING_AUTHOR_NAME);

        verify(authorDao, times(1)).getByName(EXISTING_AUTHOR_NAME);
        verify(authorDao, never()).create(EXISTING_AUTHOR_NAME);
    }

    @Test
    @DisplayName("Создать автора в БД, которого нет ")
    void shouldCreateAuthor() {
        when(authorDao.getByName(EXISTING_AUTHOR_NAME))
                .thenReturn(java.util.Optional.empty());

        when(authorDao.create(EXISTING_AUTHOR_NAME))
                .thenReturn(new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME));

        authorService.getByName(EXISTING_AUTHOR_NAME);

        verify(authorDao, times(1)).create(EXISTING_AUTHOR_NAME);
        verify(authorDao, times(1)).getByName(EXISTING_AUTHOR_NAME);
    }
}