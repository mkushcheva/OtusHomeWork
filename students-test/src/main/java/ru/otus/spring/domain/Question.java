package ru.otus.spring.domain;

import static java.lang.System.lineSeparator;

public class Question {
    private int number;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private int rightNumber;

    public Question(int number, String question, String answer1, String answer2, String answer3, int rightNumber) {
        this.number = number;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.rightNumber = rightNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int QuestionNumber) {
        this.number = QuestionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }


    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }


    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public int getRightNumber() {
        return rightNumber;
    }

    public void setRightNumber(int rightNumber) {
        this.rightNumber = rightNumber;
    }

    @Override
    public String toString() {
        return "Вопрос №" + number + ":" + lineSeparator() + question + lineSeparator()
                + "Варианты ответов:" + lineSeparator() + "1. " + answer1 + lineSeparator()
                + "2. " + answer2 + lineSeparator()
                + "3. " + answer3 + lineSeparator();
    }
}
