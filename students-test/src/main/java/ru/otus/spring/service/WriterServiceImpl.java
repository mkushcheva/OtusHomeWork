package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;

import java.util.List;

import static java.lang.System.lineSeparator;

@Service
public class WriterServiceImpl implements WriterService {
    @Override
    public void printAllQuestionAndAnswers(List<Question> questions) {
        questions.forEach(question -> {
            System.out.println(
                    lineSeparator() + "Вопрос №" + question.getNumber() + ":"
                            + lineSeparator() + question.getQuestion()
                            + lineSeparator() + "Варианты ответов:"
            );
            question.getAnswers().forEach(System.out::println);
        });
    }

    @Override
    public void printQuestionAndAnswerList(Question question) {
        int i = 1;

        System.out.println(
                lineSeparator() + "Вопрос №" + question.getNumber() + ":"
                        + lineSeparator() + question.getQuestion()
                        + lineSeparator() + "Варианты ответов:"
        );

        for (String answer : question.getAnswers()) {
            System.out.println(i + "." + answer);
            i++;
        }
    }

    @Override
    public void printStudentTestResult(Student student) {
        System.out.println(
                lineSeparator() + "Student: " + student.getSurname() + " " + student.getName() + lineSeparator()
                        + "Correct answers : " + student.getCountAnswer() + lineSeparator()
                        + (student.getTestResult() ? "Test successful!" : "Test failed!")
        );
    }

    @Override
    public void printErrorMessage(String msg) {
        System.out.println("Testing error :" + msg);
    }
}