package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.generator.QuestionGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Question")
public class QuestionTest {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Question question = QuestionGenerator.getDefaultQuestion();

        assertEquals(QuestionGenerator.DEFAULT_NUMBER, question.getNumber());
        assertEquals(QuestionGenerator.DEFAULT_QUESTION, question.getQuestion());
        assertEquals(QuestionGenerator.DEFAULT_RIGHTNUMBER, question.getRightNumber());
        assertEquals(QuestionGenerator.getDefaultAnswers(), question.getAnswers());
    }
}
