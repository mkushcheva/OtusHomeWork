package ru.otus.spring.domain;

import java.util.List;

public class Question {
    private int number;
    private String question;
    private int rightNumber;
    private List<String> answers;

    public Question(int number, String question, int rightNumber, List<String> answers) {
        this.number = number;
        this.question = question;
        this.answers = answers;
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

    public int getRightNumber() {
        return rightNumber;
    }

    public void setRightNumber(int rightNumber) {
        this.rightNumber = rightNumber;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
