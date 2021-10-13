package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Student;
import ru.otus.spring.utils.MessageSourceUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final StudentService studentService;
    private final MessageSourceUtils messageSource;

    @Override
    public Student getStudent() throws IOException {
        System.out.println(messageSource.getMessage("student.surname"));
        String surname = bufferedReader.readLine();
        System.out.println(messageSource.getMessage("student.name"));
        String name = bufferedReader.readLine();

        return studentService.createStudent(surname, name);
    }

    @Override
    public int getAnswer() throws IOException {
        System.out.println(messageSource.getMessage("student.answered"));
        return Integer.parseInt(bufferedReader.readLine());
    }
}