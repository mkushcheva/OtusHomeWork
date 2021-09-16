package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;
    private final Resource resourceFile;

    public QuestionServiceImpl(QuestionDao dao, @Value("${template}") Resource resourceFile) {
        this.dao = dao;
        this.resourceFile = resourceFile;
    }

    //Вывод вопросов на экран. Первое ДЗ
    @Override
    public void printQuestion() throws IOException {
        //получить лист вопросов
        List<Question> questions2 = dao.readQuestionsFromFileNew(resourceFile.getFile());

        System.out.println("<<<<<<<<<<<Сформировано по новому>>>>>>>>>>>>>>");
        //вывести их на экран
        for (Question question2 : questions2) {
            System.out.println(question2.toString());
        }
    }

    //Получить все вопросы. 2 ДЗ
    @Override
    public List<Question> getQuestion() throws IOException {
        return dao.readQuestionsFromFileNew(resourceFile.getFile());
    }
}
