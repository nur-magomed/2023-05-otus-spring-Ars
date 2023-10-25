package edu.nur.homework04.service;

import edu.nur.homework04.config.AppProps;
import edu.nur.homework04.model.Answer;
import edu.nur.homework04.model.QuizResults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(AppProps.class)
public class QuizResultsServiceImpl implements QuizResultsService {

    private final int minPassScore;

    public QuizResultsServiceImpl(@Value("${quiz-app.min-pass-score}")int minPassScore) {
        this.minPassScore = minPassScore;
    }

    @Override
    public int correctAnswersCount(QuizResults quizResults) {
        return (int) quizResults.getUserAnswers().values().stream().filter(Answer::isCorrect).count();
    }

    @Override
    public boolean isPassed(QuizResults quizResults) {
        return correctAnswersCount(quizResults) >= minPassScore;
    }
}
