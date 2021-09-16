package ru.otus.spring.service;

import ru.otus.spring.domain.Student;

import java.io.IOException;

/**
 * интерфейс сервиса для чтения потока ввода/вывода
 */

public interface ReaderService {
    //получение информации о тестируемом студенте
    Student getStudent() throws IOException;

    //получение ответа студента
    int getAnswer() throws IOException;
}
