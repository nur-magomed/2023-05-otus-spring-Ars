package edu.nur.service;

import edu.nur.dao.QuestionDao;
import edu.nur.exception.ReaderException;
import edu.nur.model.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getQuestions() throws ReaderException {
        return dao.getQuestions();
    }
}
