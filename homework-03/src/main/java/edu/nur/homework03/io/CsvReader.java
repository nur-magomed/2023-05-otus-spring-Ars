package edu.nur.homework03.io;

import com.opencsv.CSVReader;
import edu.nur.homework03.config.AppProps;
import edu.nur.homework03.exception.CsvReaderException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@Service
@EnableConfigurationProperties(AppProps.class)
public class CsvReader implements Reader {

    private final AppProps appProps;

    public CsvReader(AppProps appProps) {
        this.appProps = appProps;
    }

    @Override
    public List<String[]> readAllLines() throws CsvReaderException {
        try (InputStream is = Objects.requireNonNull(getClass().getClassLoader()
                .getResourceAsStream(appProps.getFileCsvPath()));
             CSVReader reader = new CSVReader(new InputStreamReader(is))) {
            return reader.readAll();
        } catch (Exception e) {
            throw new CsvReaderException("Failed to read the file: " + appProps.getFileCsvPath(), e);
        }
    }

}
