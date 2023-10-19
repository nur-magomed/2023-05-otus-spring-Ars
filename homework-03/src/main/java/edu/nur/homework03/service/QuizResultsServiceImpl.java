package edu.nur.homework03.service;

import edu.nur.homework03.config.AppProps;
import edu.nur.homework03.model.Answer;
import edu.nur.homework03.model.QuizResults;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(AppProps.class)
public class QuizResultsServiceImpl implements QuizResultsService {

    private final AppProps appProps;

    public QuizResultsServiceImpl(AppProps appProps) {
        this.appProps = appProps;
    }

    @Override
    public int correctAnswersCount(QuizResults quizResults) {
        return (int) quizResults.getUserAnswers().values().stream().filter(Answer::isCorrect).count();
    }

    @Override
    public boolean isPassed(QuizResults quizResults) {
        return correctAnswersCount(quizResults) >= appProps.getMinPassScore();
    }
}
