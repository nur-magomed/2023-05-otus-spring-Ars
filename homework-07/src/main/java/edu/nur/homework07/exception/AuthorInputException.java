package edu.nur.homework07.exception;

public class AuthorInputException extends RuntimeException {

    public AuthorInputException(String message) {
        super(message);
    }

    public AuthorInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
