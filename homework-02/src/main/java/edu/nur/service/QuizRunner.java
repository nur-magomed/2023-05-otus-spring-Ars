package edu.nur.service;

import edu.nur.io.InputOutputService;
import edu.nur.model.Answer;
import edu.nur.model.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuizRunner {

    private final QuestionService questionService;

    private final InputOutputService inputOutputService;

    public QuizRunner(QuestionService questionService, InputOutputService inputOutputService) {
        this.questionService = questionService;
        this.inputOutputService = inputOutputService;
    }

    public void runQuiz() {
        inputOutputService.outputString("Quiz started");
        inputOutputService.outputString("Last name");
        String lastName = inputOutputService.inputString();
        inputOutputService.outputString("First name");
        String firstName = inputOutputService.inputString();

        inputOutputService.outputString(lastName);
        inputOutputService.outputString(firstName);
        List<Question> questions = questionService.getQuestions();

        Map<Integer, Integer> userChoices = new HashMap<>();
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
            inputOutputService.outputString("Input choice number between 1 and " + answers.size());
            int choiceId = inputOutputService.inputInt();
            inputOutputService.outputString(String.valueOf(answerIds.get(choiceId).isCorrect()));
        }

    }

}
