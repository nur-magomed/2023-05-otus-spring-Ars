package edu.nur.homework04.service;

public interface InputValidationService {

    void validateNotEmpty(String input);

    void validateInteger(String input);

    void validateIntInRange(int input, int min, int max);

}
