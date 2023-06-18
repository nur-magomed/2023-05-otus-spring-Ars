package edu.nur.io;

import com.opencsv.CSVReader;
import lombok.Data;

import java.io.FileReader;
import java.net.URL;
import java.util.List;
import java.util.Collections;

@Data
public class CsvReader implements Reader {

    private final String fileName;

    public CsvReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<String[]> readAllLines() {
        ClassLoader cl = CsvReader.class.getClassLoader();
        URL url = cl.getResource(fileName);

        try (CSVReader reader = new CSVReader(new FileReader(url.getFile()))) {
            return reader.readAll();
        } catch (Exception e) {
            System.err.println("Failed to read a file: " + e);
        }

        return Collections.emptyList();
    }
}
