package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Student;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao dao;

    public StudentServiceImpl(StudentDao dao) {
        this.dao = dao;
    }

    @Override
    public Student createStudent(String surname, String name) {
        return dao.addStudent(surname, name);
    }
}
