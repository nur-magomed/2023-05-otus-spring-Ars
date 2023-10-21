package edu.nur.homework03.service;

import edu.nur.homework03.exception.InputException;
import org.springframework.stereotype.Service;

@Service
public class InputValidationServiceImpl implements InputValidationService {

    private final LocalizationService locService;

    public InputValidationServiceImpl(LocalizationService locService) {
        this.locService = locService;
    }

    public void validateNotEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new InputException(locService.getMessage("error-empty-input"));
        }
    }

    public void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ignore) {
            throw new InputException(locService.getMessage("error-not-integer"));
        }
    }

    public void validateIntInRange(int input, int min, int max) {
        if (min > max) {
            throw new InputException(locService.getMessage("error-min-max-args"));
        }

        if (input < min || input > max) {
            throw new InputException(locService.getMessage("error-range-from-x-to-x", new Integer[]{min, max}));
        }
    }

}