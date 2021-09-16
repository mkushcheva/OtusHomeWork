package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.service.TestingService;

import java.io.IOException;

@ComponentScan(basePackages = "ru.otus")
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        TestingService service = context.getBean(TestingService.class);
        try {
            service.testing();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
