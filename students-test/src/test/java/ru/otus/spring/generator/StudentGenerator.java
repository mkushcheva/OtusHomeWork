package ru.otus.spring.generator;

import ru.otus.spring.domain.Student;

public class StudentGenerator {
    public static final String SURNAME = "Иванов";
    public static final String NAME = "Сергей";

    public static Student getDefaultStudent() {
        return new Student(SURNAME, NAME);
    }
}
