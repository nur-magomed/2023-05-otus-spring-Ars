package edu.nur.homework04.exception;

public class InputException extends RuntimeException {

    public InputException(String message) {
        super(message);
    }

    public InputException(String message, Throwable cause) {
        super(message, cause);
    }

}
