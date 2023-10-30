package edu.nur.homework07.service.validator;

import edu.nur.homework07.exception.GenreInputException;

public class GenreInputValidator {

    public static void validateSaveInput(String title) {
        if (title.isEmpty()) {
            throw new GenreInputException("Title is empty");
        }
    }
}
