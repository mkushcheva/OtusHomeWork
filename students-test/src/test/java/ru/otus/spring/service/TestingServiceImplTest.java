package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.config.ApplicationConfig;
import ru.otus.spring.generator.QuestionGenerator;
import ru.otus.spring.generator.StudentGenerator;
import ru.otus.spring.utils.MessageSourceUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс TestingServiceImplTest")
public class TestingServiceImplTest {
    private TestingServiceImpl testingService;

    @Mock
    private ReaderService readerService;
    @Mock
    private QuestionService questionService;
    @Mock
    private WriterService writerService;
    @Mock
    private ApplicationConfig config;
    @Mock
    private MessageSourceUtils messageSource;

    @BeforeEach
    void setUp() {
        testingService = new TestingServiceImpl(readerService, writerService, questionService, config, messageSource);
    }

    @DisplayName("протестировать студента с результатом Успешно пройден тест")
    @Test
    void shouldTestStudentWithResultSuccessfully() throws IOException {
        when(readerService.getStudent()).thenReturn(StudentGenerator.getDefaultStudent());
        when(questionService.getQuestion()).thenReturn(QuestionGenerator.getDefaultQuestionList());
        when(readerService.getAnswer()).thenReturn(1);
        when(config.getCount()).thenReturn(3);

        testingService.testing();

        verify(readerService, times(1)).getStudent();
        verify(questionService, times(1)).getQuestion();
        verify(writerService, times(3)).printQuestionAndAnswerList(any());
        verify(readerService, times(3)).getAnswer();

        assertNotNull(readerService.getStudent());

        assertAll("student",
                () -> assertEquals(StudentGenerator.SURNAME, readerService.getStudent().getSurname()),
                () -> assertEquals(StudentGenerator.NAME, readerService.getStudent().getName()),
                () -> assertEquals(3, readerService.getStudent().getCountAnswer()),
                () -> assertTrue(readerService.getStudent().getTestResult())
        );
    }

    @DisplayName("протестировать студента с результатом тест не пройден")
    @Test
    void shouldTestStudentWithResultFailed() throws IOException {
        when(readerService.getStudent()).thenReturn(StudentGenerator.getDefaultStudent());
        when(questionService.getQuestion()).thenReturn(QuestionGenerator.getDefaultQuestionList());
        when(readerService.getAnswer()).thenReturn(1, 1, 3);
        when(config.getCount()).thenReturn(5);
        testingService.testing();

        verify(readerService, times(1)).getStudent();
        verify(questionService, times(1)).getQuestion();
        verify(writerService, times(3)).printQuestionAndAnswerList(any());
        verify(readerService, times(3)).getAnswer();

        assertNotNull(readerService.getStudent());

        assertAll("student",
                () -> assertEquals(StudentGenerator.SURNAME, readerService.getStudent().getSurname()),
                () -> assertEquals(StudentGenerator.NAME, readerService.getStudent().getName()),
                () -> assertEquals(2, readerService.getStudent().getCountAnswer()),
                () -> assertFalse(readerService.getStudent().getTestResult())
        );
    }
}