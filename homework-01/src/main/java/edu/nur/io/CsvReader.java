package edu.nur.io;

import com.opencsv.CSVReader;
import lombok.Data;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
public class CsvReader implements Reader {

    private final String fileName;

    public CsvReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<String[]> readAllLines() {
        try (InputStream inputStream = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName));
             CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            return reader.readAll();
        } catch (Exception e) {
            System.err.println("Failed to read a file: " + e);
        }

        return Collections.emptyList();
    }

}
