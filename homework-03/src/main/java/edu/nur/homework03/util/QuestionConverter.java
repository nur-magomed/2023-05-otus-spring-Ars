package edu.nur.homework03.util;

import edu.nur.homework03.model.Question;

import java.util.List;

public interface QuestionConverter {

    List<Question> convertToQuestions(List<String[]> lines);

}
