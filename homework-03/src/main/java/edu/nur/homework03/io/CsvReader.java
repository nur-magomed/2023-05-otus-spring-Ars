package edu.nur.homework03.io;

import com.opencsv.CSVReader;
import edu.nur.homework03.exception.CsvReaderException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public record CsvReader(String fileName) implements Reader {

    @Override
    public List<String[]> readAllLines() throws CsvReaderException {
        try (InputStream is = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName));
             CSVReader reader = new CSVReader(new InputStreamReader(is))) {
            return reader.readAll();
        } catch (Exception e) {
            throw new CsvReaderException("Failed to read the file: " + fileName, e);
        }
    }

}
