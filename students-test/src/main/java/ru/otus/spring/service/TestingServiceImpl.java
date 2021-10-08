package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.ApplicationConfig;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {
    private final ReaderService readerService;
    private final WriterService writerService;
    private final QuestionService questionService;
    private final ApplicationConfig config;
    private final MessageSource messageSource;

    @Override
    public void testing() {
        List<Integer> rightAnswers = new ArrayList<>();
        List<Integer> studentsAnswers = new ArrayList<>();

        try {
            Student student = readerService.getStudent();
            List<Question> questions = questionService.getQuestion();

            for (Question question : questions) {
                rightAnswers.add(question.getRightNumber());
                writerService.printQuestionAndAnswerList(question);
                studentsAnswers.add(readerService.getAnswer());
            }

            analysisTestResults(student, studentsAnswers, rightAnswers);
            writerService.printStudentTestResult(student);
        } catch (Exception e) {
            writerService.printErrorMessage(e.getMessage());
        }
    }

    private void analysisTestResults(Student student, List<Integer> studentsAnswers, List<Integer> rightAnswers) {
        int studentCount = 0;
        for (int i = 0; i < studentsAnswers.size(); i++) {
            if (rightAnswers.get(i).equals(studentsAnswers.get(i))) {
                studentCount++;
            }
        }

        student.setCountAnswer(studentCount);
        student.setTestResult(studentCount >= config.getCount());
    }
}