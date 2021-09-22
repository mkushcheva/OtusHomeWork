package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Student;
import ru.otus.spring.generator.StudentGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс StudentDaoSimple")
public class StudentDaoSimpleTest {

    @DisplayName("корректно создаётся студента")
    @Test
    void shouldHaveCorrectAddStudent() {
        StudentDaoSimple dao = new StudentDaoSimple();
        Student student = dao.addStudent(StudentGenerator.SURNAME, StudentGenerator.NAME);

        assertEquals(StudentGenerator.SURNAME, student.getSurname());
        assertEquals(StudentGenerator.NAME, student.getName());
    }
}
