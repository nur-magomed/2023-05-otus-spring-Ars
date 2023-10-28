package edu.nur.homework03.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class QuizResults {

    private Student student;

    private Map<Long, Answer> userAnswers;

}
