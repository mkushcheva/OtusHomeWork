package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.QuestionService;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        File source = context.getResource("question.csv").getFile();

        // получить сервис с Вопросами
        QuestionService service = context.getBean(QuestionService.class);
        // вывести список вопросов
        service.printQuestion(source);
        context.close();
    }
}
