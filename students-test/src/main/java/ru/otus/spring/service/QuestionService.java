package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionService {
    void printQuestion() throws IOException;
    List<Question> getQuestion() throws IOException;
}
