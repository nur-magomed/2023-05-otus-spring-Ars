package edu.nur.util;

import edu.nur.exception.InputException;

public class InputValidator {

    public static void validateNotEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new InputException("Your input is empty");
        }
    }

    public static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ignore) {
            throw new InputException("Your input is not integer");
        }
    }

    public static void validateIntInRange(int input, int min, int max) {
        if (min > max) {
            throw new InputException("Min and Max arguments are not correct");
        }

        if (input < min || input > max) {
            throw new InputException("Your input is not in range from " + min + " to " + max);
        }
    }

}