package edu.nur.service;

import edu.nur.model.Answer;
import edu.nur.model.QuizResults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/quiz-config.properties")
public class QuizResultsServiceImpl implements QuizResultsService {

    @Value("${minimum.pass.score}")
    private int minPassScore;

    @Override
    public int correctAnswersCount(QuizResults quizResults) {
        return (int) quizResults.getUserAnswers().values().stream().filter(Answer::isCorrect).count();
    }

    @Override
    public boolean isPassed(QuizResults quizResults) {
        return correctAnswersCount(quizResults) >= minPassScore;
    }
}
