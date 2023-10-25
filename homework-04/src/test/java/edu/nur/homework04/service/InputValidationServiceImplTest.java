package edu.nur.homework04.service;

import edu.nur.homework04.config.AppProps;
import edu.nur.homework04.exception.InputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InputValidator class validates input ")
@EnableConfigurationProperties(value = AppProps.class)
@TestPropertySource("classpath:application.yml")
@SpringBootTest(properties = {"spring.shell.interactive.enabled=false"})
class InputValidationServiceImplTest {

    @Autowired
    private InputValidationService inputValidationService;

    @DisplayName("validates that input is not null and empty")
    @Test
    void validateNotEmpty() {
        Exception nullInputExc = assertThrows(InputException.class, () -> inputValidationService.validateNotEmpty(null));
        Exception emptyInputExc = assertThrows(InputException.class, () -> inputValidationService.validateNotEmpty(""));

        String actualNullInputMessage = nullInputExc.getMessage();
        String actualEmptyInputMessage = emptyInputExc.getMessage();
        String expectedMessage = "Your input is empty";

        assertTrue(actualNullInputMessage.contains(expectedMessage));
        assertTrue(actualEmptyInputMessage.contains(expectedMessage));

        assertDoesNotThrow(() -> {
            inputValidationService.validateNotEmpty("some text");
            inputValidationService.validateNotEmpty("1");
        });
    }

    @DisplayName("validates that input is integer")
    @Test
    void validateInteger() {
        Exception exception = assertThrows(InputException.class, () -> inputValidationService.validateInteger("1One"));

        String actualMessage = exception.getMessage();
        String expectedMessage = "Your input is not integer";

        assertTrue(actualMessage.contains(expectedMessage));

        assertDoesNotThrow(() -> {
            inputValidationService.validateInteger("-123");
            inputValidationService.validateInteger("0");
            inputValidationService.validateInteger("123456789");
        });
    }

    @DisplayName("validates that int is in a given range")
    @Test
    void validateIntInRange() {
        Exception minMaxException = assertThrows(InputException.class,
                () -> inputValidationService.validateIntInRange(1, 21, 20));
        Exception lessInputException = assertThrows(InputException.class,
                () -> inputValidationService.validateIntInRange(1, 10, 20));
        Exception moreInputException = assertThrows(InputException.class,
                () -> inputValidationService.validateIntInRange(100, 10, 20));

        String actualMinMaxMessage = minMaxException.getMessage();
        String actualLessInputMessage = lessInputException.getMessage();
        String actualMoreInputMessage = moreInputException.getMessage();
        String expectedMessage = "Your input is not in range from";
        String minMaxExpectedMessage = "Min and Max arguments are not correct";

        assertTrue(actualMinMaxMessage.contains(minMaxExpectedMessage));
        assertTrue(actualLessInputMessage.contains(expectedMessage));
        assertTrue(actualMoreInputMessage.contains(expectedMessage));

        assertDoesNotThrow(() -> {
            inputValidationService.validateIntInRange(-10, -20, 0);
            inputValidationService.validateIntInRange(10, 10, 20);
            inputValidationService.validateIntInRange(15, 10, 20);
            inputValidationService.validateIntInRange(20, 10, 20);
            inputValidationService.validateIntInRange(1, 1, 1);
        });
    }
}