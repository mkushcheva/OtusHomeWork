package ru.otus.spring.service;

import java.io.IOException;

/**
 * интерфейс сервиса для тестирования студентов
 */
public interface TestingService {
    //метод запуска тестирования
    void testing() throws IOException;
}
