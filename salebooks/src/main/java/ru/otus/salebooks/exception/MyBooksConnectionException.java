package ru.otus.salebooks.exception;

public class MyBooksConnectionException extends RuntimeException{

    public MyBooksConnectionException(String message, Throwable e) {
        super(message, e);
    }
}
