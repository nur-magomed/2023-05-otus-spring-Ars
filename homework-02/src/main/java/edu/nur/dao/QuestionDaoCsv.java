package edu.nur.dao;

import edu.nur.exception.QuestionDaoException;
import edu.nur.io.Reader;
import edu.nur.model.Question;
import edu.nur.util.QuestionConverter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDaoCsv implements QuestionDao {

    private final Reader reader;

    private final QuestionConverter converter;

    public QuestionDaoCsv(Reader reader, QuestionConverter questionConverter) {
        this.reader = reader;
        this.converter = questionConverter;
    }

    @Override
    public List<Question> getQuestions() {
        try {
            List<String[]> lines = reader.readAllLines();
            return converter.convertToQuestions(lines);
        } catch (Exception e) {
            throw new QuestionDaoException("Failed to get questions.", e);
        }
    }

}
