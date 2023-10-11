package edu.nur.model;

import lombok.Data;

import java.util.Map;

@Data
public class QuizResults {

    private Student student;

    private Map<Long, Answer> userAnswers;


    public QuizResults(Student student, Map<Long, Answer> userAnswers) {
        this.student = student;
        this.userAnswers = userAnswers;
    }

}
