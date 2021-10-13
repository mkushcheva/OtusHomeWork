package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.domain.Student;
import ru.otus.spring.utils.MessageSourceUtils;

import java.io.IOException;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private Student student;
    private final StudentService studentService;
    private final TestingService testingService;
    private final MessageSourceUtils messageSource;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(String surname, String name) {
        student = studentService.createStudent(surname, name);
        return messageSource.getMessage("shell.student.welcome", new Object[]{student.getSurname(), student.getName()});
    }

    @ShellMethod(value = "Start testing command", key = {"s", "start"})
    @ShellMethodAvailability(value = "isStartTestingCommandAvailable")
    public String testing() throws IOException {
        testingService.testing(student);
        return messageSource.getMessage("shell.testing.end");
    }

    private Availability isStartTestingCommandAvailable() {
        return studentService.testingAllowed(student) ? Availability.available() : Availability.unavailable(messageSource.getMessage("shell.student.notlogin"));
    }
}