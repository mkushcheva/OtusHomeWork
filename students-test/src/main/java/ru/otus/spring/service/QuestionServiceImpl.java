package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public void printQuestion(File file) {
        //получить лист вопросов
        List<Question> questions2 = dao.readQuestionsFromFileNew(file);

        System.out.println("<<<<<<<<<<<Сформировано по новому>>>>>>>>>>>>>>");
        //вывести их на экран
        for (Question question2 : questions2) {
            System.out.println(question2.toString());
        }
    }

}
