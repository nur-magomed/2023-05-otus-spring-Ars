package edu.nur.homework03.exception;

public class CsvReaderException extends ReaderException {

    public CsvReaderException(String message) {
        super(message);
    }

    public CsvReaderException(String message, Throwable cause) {
        super(message, cause);
    }

}
