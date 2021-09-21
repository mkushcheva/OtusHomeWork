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
    private final WriterService writerService;

    public QuestionServiceImpl(QuestionDao dao, @Value("${template}") Resource resourceFile, WriterService writerService) {
        this.dao = dao;
        this.resourceFile = resourceFile;
        this.writerService = writerService;
    }

    //Вывод вопросов на экран. Первое ДЗ
    @Override
    public void printQuestion() throws IOException {
        writerService.printAllQuestionAndAnswers(dao.readQuestionsFromFileNew(resourceFile.getFile()));
    }

    //Получить все вопросы. 2 ДЗ
    @Override
    public List<Question> getQuestion() throws IOException {
        return dao.readQuestionsFromFileNew(resourceFile.getFile());
    }
}
