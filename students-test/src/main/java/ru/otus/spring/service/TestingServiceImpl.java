package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {
    private final ReaderService readerService;
    private final QuestionServiceImpl questionService;
    private final String count;

    public TestingServiceImpl(ReaderService readerService, QuestionServiceImpl questionService, @Value("${count}") String count) {
        this.readerService = readerService;
        this.questionService = questionService;
        this.count = count;
    }

    @Override
    public void testing() {
        List<Integer> rightAnswers = new ArrayList<>();
        List<Integer> studentsAnswers = new ArrayList<>();

        try {
            Student student = readerService.getStudent();
            List<Question> questions = questionService.getQuestion();
            for (Question question : questions) {
                rightAnswers.add(question.getRightNumber());
                System.out.println(question);
                studentsAnswers.add(readerService.getAnswer());
            }
            analysisTestResults(student, studentsAnswers, rightAnswers);
            System.out.println(student);
        } catch (Exception e) {
            System.out.println("Testing error :" + e.getMessage());
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

        if (studentCount >= Integer.parseInt(count)) {
            student.setTestResult(true);
        } else {
            student.setTestResult(false);
        }
    }
}
