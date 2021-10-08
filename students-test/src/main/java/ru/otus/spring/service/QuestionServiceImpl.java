package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.ApplicationConfig;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;
    private final WriterService writerService;
    private final ApplicationConfig config;


    //Вывод вопросов на экран. Первое ДЗ
    @Override
    public void printQuestion() throws IOException {
        writerService.printAllQuestionAndAnswers(dao.readQuestionsFromFileNew(getFileName()));
    }

    //Получить все вопросы. 2 ДЗ
    @Override
    public List<Question> getQuestion() throws IOException {
        return dao.readQuestionsFromFileNew(getFileName());
    }

    private File getFileName() {
        if (LocaleContextHolder.getLocale().toLanguageTag().equals("ru-RU")) {
            return config.getFileName_ru_RU();
        } else {
            return config.getFileName();
        }
    }
}
