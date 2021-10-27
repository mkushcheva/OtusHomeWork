package ru.otus.spring.service;

import ru.otus.spring.domain.Student;

import java.io.IOException;

/**
 * интерфейс сервиса для тестирования студентов
 */
public interface TestingService {
    //метод запуска тестирования
    void testing(Student student);
}
