package edu.nur.homework04.io;

import com.opencsv.CSVReader;
import edu.nur.homework04.exception.CsvReaderException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Component
public class CsvReader implements Reader {

    private final String fileName;

    public CsvReader(@Value("${quiz-app.csv-file-name-template}")String fileNameTemplate,
                     @Value("${quiz-app.locale}")Locale locale) {
        this.fileName = getLocalizedFileName(fileNameTemplate, locale);
    }

    private String getLocalizedFileName(String fileNameTemplate, Locale locale) {
        return fileNameTemplate.replace("xx", locale.getLanguage());
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
