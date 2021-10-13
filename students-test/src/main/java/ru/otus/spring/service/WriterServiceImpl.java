package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;
import ru.otus.spring.utils.MessageSourceUtils;

import java.util.List;

import static java.lang.System.lineSeparator;

@Service
@RequiredArgsConstructor
public class WriterServiceImpl implements WriterService {
    private final MessageSourceUtils messageSource;

    @Override
    public void printAllQuestionAndAnswers(List<Question> questions) {

        questions.forEach(question -> {
            System.out.println(
                    messageSource.getMessage("testing.question", new Object[]{question.getNumber(), question.getQuestion()})
                            + lineSeparator() + messageSource.getMessage("testing.choiceAnswer")
            );
            question.getAnswers().forEach(System.out::println);
        });
    }

    @Override
    public void printQuestionAndAnswerList(Question question) {
        int i = 1;

        System.out.println(
                messageSource.getMessage("testing.question", new Object[]{question.getNumber(), question.getQuestion()})
                        + lineSeparator() + messageSource.getMessage("testing.choiceAnswer")
        );

        for (String answer : question.getAnswers()) {
            System.out.println(i + "." + answer);
            i++;
        }
    }

    @Override
    public void printStudentTestResult(Student student) {
        String fio = student.getSurname() + " " + student.getName();

        System.out.println(
                messageSource.getMessage("student.fio", new Object[]{fio}) + lineSeparator() +
                        messageSource.getMessage("testing.result.count", new Object[]{student.getCountAnswer()}) + lineSeparator() +
                        (student.getTestResult()
                                ? messageSource.getMessage("testing.result.successful")
                                : messageSource.getMessage("testing.result.failed")
                        )
        );
    }

    @Override
    public void printErrorMessage(String msg) {
        System.out.println(messageSource.getMessage("testing.error", new Object[]{msg}));
    }
}