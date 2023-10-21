package edu.nur.service;

import edu.nur.model.QuizResults;

public interface QuizResultsService {

    int correctAnswersCount(QuizResults quizResults);

    boolean isPassed(QuizResults quizResults);
}
