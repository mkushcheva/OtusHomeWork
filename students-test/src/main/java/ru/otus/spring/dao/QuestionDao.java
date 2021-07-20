package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface QuestionDao {
    List<Question> readQuestionsFromFile(File file) throws IOException;
}
