package edu.nur.homework03.dao;

import edu.nur.homework03.io.Reader;
import edu.nur.homework03.model.Question;
import edu.nur.homework03.util.QuestionConverter;
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
        List<String[]> lines = reader.readAllLines();
        return converter.convertToQuestions(lines);
    }

}
