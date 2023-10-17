package edu.nur.homework03.service;

import edu.nur.homework03.exception.InputException;
import edu.nur.homework03.io.InputOutputService;
import edu.nur.homework03.model.Answer;
import edu.nur.homework03.model.Question;
import edu.nur.homework03.model.QuizResults;
import edu.nur.homework03.model.Student;
import edu.nur.homework03.util.InputValidator;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class QuizRunner implements ApplicationRunner{

    private final QuestionService questionService;

    private final InputOutputService inputOutputService;

    private final QuizResultsService quizResultsService;

    public QuizRunner(QuestionService questionService, InputOutputService inputOutputService,
                      QuizResultsService quizResultsService) {
        this.questionService = questionService;
        this.inputOutputService = inputOutputService;
        this.quizResultsService = quizResultsService;
    }

    public void runQuiz() {
        inputOutputService.outputString("Quiz started");
        String lastName = askStudentDetails("Please enter your last name: ");
        String firstName = askStudentDetails("Please enter your first name: ");
        Student student = new Student(firstName, lastName);
        List<Question> questions = questionService.getQuestions();
        Map<Long, Answer> userAnswers = new HashMap<>();
        for (Question q: questions) {
            Set<Answer> answers = q.getAnswers();
            Map<Integer, Answer> answerIds = new HashMap<>();
            int counter = 1;
            inputOutputService.outputString(q.getTitle());
            for (Answer a: answers) {
                inputOutputService.outputString(counter + ". " + a.getTitle());
                answerIds.put(counter, a);
                counter++;
            }
            int choiceId = askAnswerNumber("Input choice number between 1 and "
                    + answers.size() + ": ", 1, answers.size());
            userAnswers.put(q.getId(), answerIds.get(choiceId));
        }
        QuizResults quizResults = new QuizResults(student, userAnswers);
        printResult(quizResults);
    }

    private void printResult(QuizResults quizResults) {
        inputOutputService.outputString(quizResultsService.isPassed(quizResults) ? "You passed" : "You failed");
        inputOutputService.outputString("Your score: " + quizResultsService.correctAnswersCount(quizResults));
    }

    private String askStudentDetails(String outputMessage) {
        inputOutputService.outputString(outputMessage);
        while (true) {
            try {
                String input = inputOutputService.inputString();
                InputValidator.validateNotEmpty(input);
                return input;
            } catch (InputException e) {
                inputOutputService.outputString(e.getMessage());
                inputOutputService.outputString("Please try again.");
            }
        }
    }

    private int askAnswerNumber(String outputMessage, int min, int max) {
        inputOutputService.outputString(outputMessage);
        while (true) {
            try {
                String input = inputOutputService.inputString();
                InputValidator.validateNotEmpty(input);
                InputValidator.validateInteger(input);
                int answerNumber = Integer.parseInt(input);
                InputValidator.validateIntInRange(answerNumber, min, max);
                return answerNumber;
            } catch (InputException e) {
                inputOutputService.outputString(e.getMessage());
                inputOutputService.outputString("Please try again.");
            }
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        runQuiz();
    }
}
