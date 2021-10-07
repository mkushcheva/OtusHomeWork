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
    private final WriterService writerService;
    private final QuestionService questionService;
    private final String count;

    public TestingServiceImpl(ReaderService readerService, WriterService writerService, QuestionService questionService, @Value("${count}") String count) {
        this.readerService = readerService;
        this.writerService = writerService;
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
        student.setTestResult(studentCount >= Integer.parseInt(count));
    }
}