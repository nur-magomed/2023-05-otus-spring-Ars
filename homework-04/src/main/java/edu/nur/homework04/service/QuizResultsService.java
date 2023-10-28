package edu.nur.homework04.service;

import edu.nur.homework04.model.QuizResults;

public interface QuizResultsService {

    int correctAnswersCount(QuizResults quizResults);

    boolean isPassed(QuizResults quizResults);
}
