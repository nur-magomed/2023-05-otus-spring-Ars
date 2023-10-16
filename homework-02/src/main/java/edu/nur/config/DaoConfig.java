package edu.nur.config;

import edu.nur.dao.QuestionDao;
import edu.nur.dao.QuestionDaoCsv;
import edu.nur.io.CsvReader;
import edu.nur.io.Reader;
import edu.nur.util.QuestionConverter;
import edu.nur.util.QuestionConverterCsv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/quiz-config.properties")
public class DaoConfig {

    @Value("${csv.file.path}")
    private String FILE_NAME;

    @Bean
    public QuestionDao questionDao() {
        Reader csvReader = new CsvReader(FILE_NAME);
        QuestionConverter questionConverter = new QuestionConverterCsv();
        return new QuestionDaoCsv(csvReader, questionConverter);
    }

}
