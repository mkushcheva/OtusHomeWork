package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.generator.StudentGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Student")
public class StudentTest {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Student student = StudentGenerator.getDefaultStudent();

        assertEquals(StudentGenerator.SURNAME, student.getSurname());
        assertEquals(StudentGenerator.NAME, student.getName());
    }

    @DisplayName("корректно заносит количество правильоных ответов")
    @Test
    void shouldHaveCorrectAddCountAnswer() {
        Student student = StudentGenerator.getDefaultStudent();
        student.setCountAnswer(2);

        assertEquals(2, student.getCountAnswer());
    }

    @DisplayName("корректно заносит результат тестирования")
    @Test
    void shouldHaveCorrectAddTestResult() {
        Student student = StudentGenerator.getDefaultStudent();
        student.setTestResult(true);

        assertEquals(true, student.getTestResult());
    }
}
