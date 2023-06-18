package edu.nur.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CsvReader class")
class CsvReaderTest {

    private final String fileName = "quiz_test.csv";

    @DisplayName("constructor works as expected")
    @Test
    void constructorTest() {
        CsvReader reader = new CsvReader(fileName);

        assertEquals(fileName, reader.getFileName(), "file name is incorrect");
    }

    @DisplayName("readAllLines method works as expected")
    @Test
    void readAllLinesTest() {
        String[] line1 = {"1","Two plus two?","2","1","zero"};
        String[] line2 = {"1","Two plus two?","2","2","four"};
        String[] line3 = {"1","Two plus two?","2","3","five"};
        String[] line4 = {"2","What language uses Spring framework?","1","4","Java"};
        String[] line5 = {"2","What language uses Spring framework?","1","5","JavaScript"};
        String[] line6 = {"2","What language uses Spring framework?","1","6","Python"};

        CsvReader reader = new CsvReader(fileName);
        List<String[]> lines = reader.readAllLines();
        assertEquals(6, lines.size(), "");

        assertArrayEquals(lines.get(0), line1, "line 1 is not correct.");
        assertArrayEquals(lines.get(1), line2, "line 2 is not correct.");
        assertArrayEquals(lines.get(2), line3, "line 3 is not correct.");
        assertArrayEquals(lines.get(3), line4, "line 4 is not correct.");
        assertArrayEquals(lines.get(4), line5, "line 5 is not correct.");
        assertArrayEquals(lines.get(5), line6, "line 6 is not correct.");
    }
}