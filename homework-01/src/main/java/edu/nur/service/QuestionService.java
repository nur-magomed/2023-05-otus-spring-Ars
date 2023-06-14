package edu.nur.service;

import edu.nur.dao.QuestionDao;

import java.util.List;

public interface QuestionService {

    List<QuestionDao> getQuestions();

}
