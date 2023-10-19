package edu.nur.service;

import edu.nur.exception.InputException;
import edu.nur.io.InputOutputService;
import edu.nur.model.Answer;
import edu.nur.model.Question;
import edu.nur.model.QuizResults;
import edu.nur.model.Student;
import edu.nur.util.InputValidator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class QuizRunner {

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
        Student student = createStudentFromInput();
        Map<Long, Answer> userAnswers = getUserAnswers();
        QuizResults quizResults = new QuizResults(student, userAnswers);
        printResult(quizResults);
    }

    private Student createStudentFromInput() {
        String lastName = askStudentDetails("Please enter your last name: ");
        String firstName = askStudentDetails("Please enter your first name: ");
        return new Student(firstName, lastName);
    }

    private Map<Long, Answer> getUserAnswers() {
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
        return userAnswers;
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
}
