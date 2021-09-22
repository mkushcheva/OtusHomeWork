package ru.otus.spring.domain;

public class Student {
    private final String surname;
    private final String name;

    private int countAnswer;
    private Boolean testResult;

    public Student(String surname, String name) {
        this.surname = surname;
        this.name = name;
        this.testResult = false;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getCountAnswer() {
        return countAnswer;
    }

    public void setCountAnswer(int countAnswer) {
        this.countAnswer = countAnswer;
    }

    public Boolean getTestResult() {
        return testResult;
    }

    public void setTestResult(Boolean testResult) {
        this.testResult = testResult;
    }
}
