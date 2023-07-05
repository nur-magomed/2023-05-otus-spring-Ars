package edu.nur.dao;

import edu.nur.io.OutputService;
import edu.nur.io.Reader;
import edu.nur.model.Question;
import edu.nur.util.QuestionConverter;

import java.util.List;

public class QuestionDaoCsv implements QuestionDao {

    private final Reader reader;

    private final QuestionConverter converter;

    private final OutputService outputService;

    public QuestionDaoCsv(Reader reader, QuestionConverter questionConverter, OutputService outputService) {
        this.reader = reader;
        this.converter = questionConverter;
        this.outputService = outputService;
    }

    @Override
    public List<Question> getQuestions() {
        try {
            List<String[]> lines = reader.readAllLines();
            return converter.convertToQuestions(lines);
        } catch (Exception e) {
            outputService.outputString(e.getMessage());
        }
        return List.of();
    }

}
