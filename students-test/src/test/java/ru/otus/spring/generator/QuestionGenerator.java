package ru.otus.spring.generator;

import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionGenerator {
    public static final int DEFAULT_NUMBER = 1;
    public static final int DEFAULT_RIGHTNUMBER = 1;
    public static final String DEFAULT_QUESTION = "Вопрос №1";

    public static List<String> getDefaultAnswers() {
        List<String> answers = new ArrayList<>();
        answers.add("Ответ 1");
        answers.add("Ответ 2");
        answers.add("Ответ 3");
        return answers;
    }

    public static Question getDefaultQuestion() {
        return new Question(DEFAULT_NUMBER, DEFAULT_QUESTION, DEFAULT_RIGHTNUMBER, getDefaultAnswers());
    }

    public static Question getQuestionByParams(int number, String question, int righNumber) {
        return new Question(number, question, righNumber, getDefaultAnswers());
    }

    public static List<Question> getDefaultQuestionList() {
        List<Question> questionList = new ArrayList<>();

        for (int i = 1; i < 4; i++ ){
            questionList.add(getQuestionByParams(i, "Question"+i, 1));
        }
        return questionList;
    }

}
