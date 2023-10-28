package edu.nur.homework04.shell;

import edu.nur.homework04.service.LocalizationService;
import edu.nur.homework04.service.QuizRunner;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ApplicationCommands {

    private final QuizRunner quizRunner;

    private final LocalizationService localizationService;

    private String firstName;

    private String lastName;

    public ApplicationCommands(QuizRunner quizRunner, LocalizationService localizationService) {
        this.quizRunner = quizRunner;
        this.localizationService = localizationService;
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "guest") String firstName,
                        @ShellOption(defaultValue = "guest") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        return localizationService.getMessage("welcome-fname-lname", new Object[]{firstName, lastName});
    }

    @ShellMethod(value = "Start quiz command", key = {"s", "start"})
    @ShellMethodAvailability(value = "isLoggedIn")
    public void runQuiz() {
        quizRunner.runQuiz(firstName, lastName);
    }

    private Availability isLoggedIn() {
        return firstName == null || lastName == null
                ? Availability.unavailable(localizationService.getMessage("please-log-in"))
                : Availability.available();
    }
}
