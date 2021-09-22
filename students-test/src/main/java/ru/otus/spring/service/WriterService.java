package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;

import java.util.List;

/**
 * интерфейс сервиса для вывода сообщений на экран
 */

public interface WriterService {
    //напечатать список всех вопросов
    void printAllQuestionAndAnswers(List<Question> questions);

    //напечатать вопрос и варианты ответа на него
    void printQuestionAndAnswerList(Question question);

    //напечатать результат тестирования студента
    void printStudentTestResult(Student student);

    //напечатать текст ошибки
    void printErrorMessage(String msg);

}
