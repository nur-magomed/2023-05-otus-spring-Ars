package edu.nur.homework06.service.validator;

import edu.nur.homework06.exception.CommentInputException;
import edu.nur.homework06.model.Book;

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
