package edu.nur.dao;

import edu.nur.exception.ReaderException;
import edu.nur.model.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getQuestions() throws ReaderException;

}
