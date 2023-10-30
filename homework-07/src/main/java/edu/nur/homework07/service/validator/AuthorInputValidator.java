package edu.nur.homework07.service.validator;

import edu.nur.homework07.exception.AuthorInputException;

public class AuthorInputValidator {

    public static void validateSaveInput(String firstName, String lastName, String birthDate) {
        if (firstName.isEmpty()) {
            throw new AuthorInputException("First name is not provided");
        }
        if (lastName.isEmpty()) {
            throw new AuthorInputException("Last name is not provided");
        }
        if (birthDate.isEmpty()) {
            throw new AuthorInputException("Birthdate is not provided");
        }
    }
}
