package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import ru.otus.spring.dao.QuestionResource;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionResource questionResource = context.getBean(QuestionResource.class);
        Resource resource = questionResource.getResource();

        try {
            // получить сервис с Вопросами
            QuestionService service = context.getBean(QuestionService.class);
            // вывести список вопросов
            service.printQuestion(resource.getFile());
            context.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
