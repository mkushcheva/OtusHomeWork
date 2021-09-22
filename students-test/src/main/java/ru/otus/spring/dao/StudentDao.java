package ru.otus.spring.dao;

import ru.otus.spring.domain.Student;

/**
 * интерфейс для работы с объектом студенты
 */
public interface StudentDao {
    Student addStudent(String surname, String name);
}
