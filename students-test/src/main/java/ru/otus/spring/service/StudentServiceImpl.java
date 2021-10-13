package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Student;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentDao dao;

    @Override
    public Student createStudent(String surname, String name) {
        return dao.addStudent(surname, name);
    }

    @Override
    public Boolean testingAllowed(Student student) {
        return student != null && student.getName() != null && student.getSurname() != null;
    }
}
