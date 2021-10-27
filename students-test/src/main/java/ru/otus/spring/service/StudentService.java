package ru.otus.spring.service;

import ru.otus.spring.domain.Student;

/**
 * интерфейс сервиса для работы со студентами
 */
public interface StudentService {
    Student createStudent(String surname, String name);

    Boolean testingAllowed(Student student);
}
