package edu.nur.homework04.exception;

public class CsvReaderException extends ReaderException {

    public CsvReaderException(String message) {
        super(message);
    }

    public CsvReaderException(String message, Throwable cause) {
        super(message, cause);
    }

}
