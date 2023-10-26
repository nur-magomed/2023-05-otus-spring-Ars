package edu.nur.homework04.service;

import edu.nur.homework04.exception.InputException;
import edu.nur.homework04.io.InputOutputService;
import edu.nur.homework04.model.Answer;
import edu.nur.homework04.model.Question;
import edu.nur.homework04.model.QuizResults;
import edu.nur.homework04.model.Student;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class QuizRunner {

    private final QuestionService questionService;

    private final InputOutputService inputOutputService;

    private final QuizResultsService quizResultsService;

    private final InputValidationService inputValidationService;

    private final LocalizationService localizationService;

    public QuizRunner(QuestionService questionService, InputOutputService inputOutputService,
                      QuizResultsService quizResultsService, InputValidationService inputValidationService,
                      LocalizationService localizationService) {
        this.questionService = questionService;
        this.inputOutputService = inputOutputService;
        this.quizResultsService = quizResultsService;
        this.inputValidationService = inputValidationService;
        this.localizationService = localizationService;
    }

    public void runQuiz(String firstName, String lastName) {
        inputOutputService.outputString(localizationService.getMessage("quiz-started"));
        Student student = new Student(firstName, lastName);
        Map<Long, Answer> userAnswers = getUserAnswers();
        QuizResults quizResults = new QuizResults(student, userAnswers);
        printResult(quizResults);
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
            int choiceId = askAnswerNumber(localizationService.getMessage("enter-choice-between-x-and-x",
                    new Integer[]{1, answers.size()}), 1, answers.size());
            userAnswers.put(q.getId(), answerIds.get(choiceId));
        }
        return userAnswers;
    }

    private void printResult(QuizResults quizResults) {
        inputOutputService.outputString(quizResultsService.isPassed(quizResults)
                ? localizationService.getMessage("you-passed")
                : localizationService.getMessage("you-failed"));
        inputOutputService.outputString(localizationService.getMessage("your-score-x",
                new Integer[]{quizResultsService.correctAnswersCount(quizResults)}));
    }

    private int askAnswerNumber(String outputMessage, int min, int max) {
        inputOutputService.outputString(outputMessage);
        while (true) {
            try {
                String input = inputOutputService.inputString();
                inputValidationService.validateNotEmpty(input);
                inputValidationService.validateInteger(input);
                int answerNumber = Integer.parseInt(input);
                inputValidationService.validateIntInRange(answerNumber, min, max);
                return answerNumber;
            } catch (InputException e) {
                inputOutputService.outputString(e.getMessage());
                inputOutputService.outputString(localizationService.getMessage("try-again"));
            }
        }
    }

}
