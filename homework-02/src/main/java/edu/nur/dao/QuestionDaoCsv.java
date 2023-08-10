package edu.nur.dao;

import edu.nur.io.Reader;
import edu.nur.model.Question;
import edu.nur.util.QuestionConverter;

import java.util.List;

public class QuestionDaoCsv implements QuestionDao {

    private final Reader reader;

    private final QuestionConverter converter;

    public QuestionDaoCsv(Reader reader, QuestionConverter questionConverter) {
        this.reader = reader;
        this.converter = questionConverter;
    }

    @Override
    public List<Question> getQuestions() {
            List<String[]> lines = reader.readAllLines();
            return converter.convertToQuestions(lines);
    }

}
