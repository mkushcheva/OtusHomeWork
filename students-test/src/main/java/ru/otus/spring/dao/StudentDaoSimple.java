package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Student;

@Component
public class StudentDaoSimple implements StudentDao{

    public Student addStudent(String surname, String name){
        return new Student(surname, name);
    }
}
