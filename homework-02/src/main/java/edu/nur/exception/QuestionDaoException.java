package edu.nur.exception;

public class QuestionDaoException extends ReaderException {

    public QuestionDaoException(String message) {
        super(message);
    }

    public QuestionDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
