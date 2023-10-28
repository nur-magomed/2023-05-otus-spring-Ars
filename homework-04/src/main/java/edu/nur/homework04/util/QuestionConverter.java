package edu.nur.homework04.util;

import edu.nur.homework04.model.Question;

import java.util.List;

public interface QuestionConverter {

    List<Question> convertToQuestions(List<String[]> lines);

}
