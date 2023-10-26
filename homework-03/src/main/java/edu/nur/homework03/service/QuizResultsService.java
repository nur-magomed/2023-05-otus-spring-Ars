package edu.nur.homework03.service;

import edu.nur.homework03.model.QuizResults;

public interface QuizResultsService {

    int correctAnswersCount(QuizResults quizResults);

    boolean isPassed(QuizResults quizResults);
}
