package edu.nur.homework04.dao;

import edu.nur.homework04.exception.QuestionDaoException;
import edu.nur.homework04.io.Reader;
import edu.nur.homework04.model.Question;
import edu.nur.homework04.util.QuestionConverter;
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
