package ru.otus.spring.service;

import java.io.File;
import java.io.IOException;

public interface QuestionService {
    void printQuestion(File source) throws IOException;
}
