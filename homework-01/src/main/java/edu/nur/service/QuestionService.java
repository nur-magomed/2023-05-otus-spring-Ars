package edu.nur.service;

import edu.nur.exception.ReaderException;
import edu.nur.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestions() throws ReaderException;

}
