package edu.nur.homework04.io;

import com.opencsv.CSVReader;
import edu.nur.homework04.config.AppProps;
import edu.nur.homework04.exception.CsvReaderException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@Service
@EnableConfigurationProperties(AppProps.class)
public class CsvReader implements Reader {

    private final String fileName;

    public CsvReader(@Value("${quiz-app.file-csv-path}")String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<String[]> readAllLines() throws CsvReaderException {
        try (InputStream is = Objects.requireNonNull(getClass().getClassLoader()
                .getResourceAsStream(fileName));
             CSVReader reader = new CSVReader(new InputStreamReader(is))) {
            return reader.readAll();
        } catch (Exception e) {
            throw new CsvReaderException("Failed to read the file: " + fileName, e);
        }
    }

}
