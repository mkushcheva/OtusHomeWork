package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ReaderServiceImpl implements ReaderService {
    private final BufferedReader bufferedReader;
    private final StudentService studentService;

    public ReaderServiceImpl(StudentService studentService) {
        this.studentService = studentService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public Student getStudent() throws IOException {
        System.out.println("Enter your surname:");
        String surname = bufferedReader.readLine();
        System.out.println("Enter your name:");
        String name = bufferedReader.readLine();

        return studentService.createStudent(surname, name);
    }

    @Override
    public int getAnswer() throws IOException {
        System.out.println("Choose the correct answer number:");
        return Integer.parseInt(bufferedReader.readLine());
    }
}
