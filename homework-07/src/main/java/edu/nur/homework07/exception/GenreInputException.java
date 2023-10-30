package edu.nur.homework07.exception;

public class GenreInputException extends RuntimeException {

    public GenreInputException(String message) {
        super(message);
    }

    public GenreInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
