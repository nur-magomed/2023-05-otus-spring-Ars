package edu.nur.homework07.service.validator;

import edu.nur.homework07.exception.CommentInputException;
import edu.nur.homework07.model.Book;

import java.util.Optional;

public class CommentInputValidator {

    public static void validateSaveInput(String message, Optional<Book> book) {
        if (message.isEmpty()) {
            throw new CommentInputException("Message is empty");
        }
        if (book.isEmpty()) {
            throw new CommentInputException("Book not found");
        }
    }
}
