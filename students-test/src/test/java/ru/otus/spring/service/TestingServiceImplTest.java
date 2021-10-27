package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.config.ApplicationConfig;
import ru.otus.spring.domain.Student;
import ru.otus.spring.generator.QuestionGenerator;
import ru.otus.spring.generator.StudentGenerator;
import ru.otus.spring.utils.MessageSourceUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс TestingServiceImplTest")
@SpringBootTest
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
        Student student = StudentGenerator.getDefaultStudent();
        when(questionService.getQuestion()).thenReturn(QuestionGenerator.getDefaultQuestionList());
        when(readerService.getAnswer()).thenReturn(1);
        when(config.getCount()).thenReturn(3);

        testingService.testing(student);

        verify(questionService, times(1)).getQuestion();
        verify(writerService, times(3)).printQuestionAndAnswerList(any());
        verify(readerService, times(3)).getAnswer();

        assertNotNull(student);

        assertAll("student",
                () -> assertEquals(StudentGenerator.SURNAME, student.getSurname()),
                () -> assertEquals(StudentGenerator.NAME, student.getName()),
                () -> assertEquals(3, student.getCountAnswer()),
                () -> assertTrue(student.getTestResult())
        );
    }

    @DisplayName("протестировать студента с результатом тест не пройден")
    @Test
    void shouldTestStudentWithResultFailed() throws IOException {
        Student student = StudentGenerator.getDefaultStudent();
        when(questionService.getQuestion()).thenReturn(QuestionGenerator.getDefaultQuestionList());
        when(readerService.getAnswer()).thenReturn(1, 1, 3);
        when(config.getCount()).thenReturn(5);

        testingService.testing(student);

        verify(questionService, times(1)).getQuestion();
        verify(writerService, times(3)).printQuestionAndAnswerList(any());
        verify(readerService, times(3)).getAnswer();

        assertNotNull(student);

        assertAll("student",
                () -> assertEquals(StudentGenerator.SURNAME, student.getSurname()),
                () -> assertEquals(StudentGenerator.NAME, student.getName()),
                () -> assertEquals(2, student.getCountAnswer()),
                () -> assertFalse(student.getTestResult())
        );
    }
}