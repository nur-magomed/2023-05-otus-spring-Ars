package edu.nur.homework07.exception;

public class BookInputException extends RuntimeException {
    public BookInputException(String message) {
        super(message);
    }

    public BookInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
