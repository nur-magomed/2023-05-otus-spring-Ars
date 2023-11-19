package edu.nur.homework07.exception;

public class CommentInputException extends RuntimeException {

    public CommentInputException(String message) {
        super(message);
    }

    public CommentInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
