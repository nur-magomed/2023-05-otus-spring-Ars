package edu.nur.util;

import edu.nur.exception.InputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InputValidator class validates input ")
class InputValidatorTest {

    @DisplayName("validates that input is not null and empty")
    @Test
    void validateNotEmpty() {
        Exception nullInputExc = assertThrows(InputException.class, () -> InputValidator.validateNotEmpty(null));
        Exception empltyInputExc = assertThrows(InputException.class, () -> InputValidator.validateNotEmpty(""));

        String actualNullInputMessage = nullInputExc.getMessage();
        String actualEmptyInputMessage = empltyInputExc.getMessage();
        String expectedMessage = "Your input is empty";

        assertTrue(actualNullInputMessage.contains(expectedMessage));
        assertTrue(actualEmptyInputMessage.contains(expectedMessage));

        assertDoesNotThrow(() -> {
            InputValidator.validateNotEmpty("some text");
            InputValidator.validateNotEmpty("1");
        });
    }

    @DisplayName("validates that input is integer")
    @Test
    void validateInteger() {
        Exception exception = assertThrows(InputException.class, () -> InputValidator.validateInteger("1One"));

        String actualMessage = exception.getMessage();
        String expectedMessage = "Your input is not integer";

        assertTrue(actualMessage.contains(expectedMessage));

        assertDoesNotThrow(() -> {
            InputValidator.validateInteger("-123");
            InputValidator.validateInteger("0");
            InputValidator.validateInteger("123456789");
        });
    }

    @DisplayName("validates that int is in a given range")
    @Test
    void validateIntInRange() {
        Exception minMaxException = assertThrows(InputException.class,
                () -> InputValidator.validateIntInRange(1, 21, 20));
        Exception lessInputException = assertThrows(InputException.class,
                () -> InputValidator.validateIntInRange(1, 10, 20));
        Exception moreInputException = assertThrows(InputException.class,
                () -> InputValidator.validateIntInRange(100, 10, 20));

        String actualMinMaxMessage = minMaxException.getMessage();
        String actualLessInputMessage = lessInputException.getMessage();
        String actualMoreInputMessage = moreInputException.getMessage();
        String expectedMessage = "Your input is not in range from";
        String minMaxExpectedMessage = "Min and Max arguments are not correct";

        assertTrue(actualMinMaxMessage.contains(minMaxExpectedMessage));
        assertTrue(actualLessInputMessage.contains(expectedMessage));
        assertTrue(actualMoreInputMessage.contains(expectedMessage));

        assertDoesNotThrow(() -> {
            InputValidator.validateIntInRange(-10, -20, 0);
            InputValidator.validateIntInRange(10, 10, 20);
            InputValidator.validateIntInRange(15, 10, 20);
            InputValidator.validateIntInRange(20, 10, 20);
            InputValidator.validateIntInRange(1, 1, 1);
        });
    }
}