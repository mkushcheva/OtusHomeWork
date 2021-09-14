package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.io.File;
import java.util.List;

public interface QuestionDao {
    List<Question> readQuestionsFromFileNew(File file);
}
