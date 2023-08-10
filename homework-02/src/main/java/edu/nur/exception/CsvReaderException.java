package edu.nur.exception;

public class CsvReaderException extends ReaderException {

    public CsvReaderException(String message) {
        super(message);
    }

    public CsvReaderException(String message, Throwable cause) {
        super(message, cause);
    }

}
