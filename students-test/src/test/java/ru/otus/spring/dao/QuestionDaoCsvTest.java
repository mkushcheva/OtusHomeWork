package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Question;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Класс QuestionDaoCsv")
public class QuestionDaoCsvTest {
    private static final String DEFAULT_FILE_NAME = "question.csv";

    @DisplayName("корректно получить список из 4 вопросов")
    @Test
    void shouldHaveCorrectGetQuestions() throws IOException {
        File file = new File(getClass().getClassLoader().getResource(DEFAULT_FILE_NAME).getFile());
        assertNotNull(file);

        QuestionDaoCsv dao = new QuestionDaoCsv();
        List<Question> question = dao.readQuestionsFromFileNew(file);

        assertNotNull(question);
        assertThat(question).hasSize(4);
    }
}
