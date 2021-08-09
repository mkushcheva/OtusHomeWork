package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.QuestionService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        Properties property = new Properties();
        FileInputStream fis;

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            String csvFileName = property.getProperty("csvFileName");

            File source = context.getResource(csvFileName).getFile();

            // получить сервис с Вопросами
            QuestionService service = context.getBean(QuestionService.class);
            // вывести список вопросов
            service.printQuestion(source);
            context.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
