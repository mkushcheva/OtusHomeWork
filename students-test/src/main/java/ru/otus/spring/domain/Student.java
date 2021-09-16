package ru.otus.spring.domain;

import static java.lang.System.lineSeparator;

public class Student {
    private final String surname;
    private final String name;

    private int countAnswer;
    private Boolean testResult;

    public Student(String surname, String name) {
        this.surname = surname;
        this.name = name;
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

    @Override
    public String toString() {
        return "Student: " + surname + " " + name + lineSeparator()
                + "Correct answers : " + countAnswer + lineSeparator()
                + (testResult ? "Test successful!" : "Test failed!");
    }
}
