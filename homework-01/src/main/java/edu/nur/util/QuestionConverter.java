package edu.nur.util;

import edu.nur.model.Question;

import java.util.List;

public interface QuestionConverter {

    List<Question> convertToQuestions(List<String[]> lines);

}
