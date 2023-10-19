package edu.nur.service;

import edu.nur.model.Answer;
import edu.nur.model.QuizResults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:/quiz-config.properties")
public class QuizResultsServiceImpl implements QuizResultsService {

    private final int minPassScore;

    public QuizResultsServiceImpl(@Value("${minimum.pass.score}") int minPassScore) {
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
