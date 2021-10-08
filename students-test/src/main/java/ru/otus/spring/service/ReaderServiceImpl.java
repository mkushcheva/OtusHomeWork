package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ReaderServiceImpl implements ReaderService {
    private final BufferedReader bufferedReader;
    private final StudentService studentService;
    private final MessageSource messageSource;

    public ReaderServiceImpl(StudentService studentService, MessageSource messageSource) {
        this.studentService = studentService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        this.messageSource = messageSource;
    }

    @Override
    public Student getStudent() throws IOException {
        System.out.println(
                messageSource.getMessage("student.surname", null, LocaleContextHolder.getLocale())
        );
        String surname = bufferedReader.readLine();
        System.out.println(
                messageSource.getMessage("student.name", null, LocaleContextHolder.getLocale())
        );
        String name = bufferedReader.readLine();

        return studentService.createStudent(surname, name);
    }

    @Override
    public int getAnswer() throws IOException {
        System.out.println(
                messageSource.getMessage("student.answered", null, LocaleContextHolder.getLocale())
        );
        return Integer.parseInt(bufferedReader.readLine());
    }
}
