package edu.nur.homework05.service.validator;

import edu.nur.homework05.exception.BookInputException;

import java.util.Arrays;
import java.util.List;

public class BookInputValidator {

    public static void validateSaveInput(String title, String authorIds, String genreId) {
        if (title.isEmpty()) {
            throw new BookInputException("Title is empty");
        }

        if (authorIds.isEmpty()) {
            throw new BookInputException("Author ids is empty");
        }
        try {
            String[] authorIdsArray = authorIds.split(",");
            List<Long> authorIdsList = Arrays.stream(authorIdsArray).map(Long::parseLong).toList();
        } catch (NumberFormatException ignore) {
            throw new BookInputException("Error in author ids");
        }

        if (genreId.isEmpty()) {
            throw new BookInputException("Genre id is empty");
        }
        try {
            Long.parseLong(genreId);
        } catch (NumberFormatException ignore) {
            throw new BookInputException("Error in genre ID");
        }
    }

}
