package edu.nur.config;

import edu.nur.dao.QuestionDao;
import edu.nur.dao.QuestionDaoCsv;
import edu.nur.io.CsvReader;
import edu.nur.io.Reader;
import edu.nur.util.QuestionConverter;
import edu.nur.util.QuestionConverterCsv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    private final String FILE_NAME = "quiz.csv";

    @Bean
    public QuestionDao questionDao() {
        Reader csvReader = new CsvReader(FILE_NAME);
        QuestionConverter questionConverter = new QuestionConverterCsv();
        return new QuestionDaoCsv(csvReader, questionConverter);
    }

}
