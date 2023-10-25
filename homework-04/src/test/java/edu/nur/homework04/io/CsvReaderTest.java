package edu.nur.homework04.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CsvReader class")
@SpringBootTest(properties = {"spring.shell.interactive.enabled=false"})
class CsvReaderTest {

    @Autowired
    private CsvReader reader;

    @DisplayName("readAllLines method works as expected")
    @Test
    void readAllLinesTest() throws Exception {
        String[] line1 = {"1","Two plus two?","1","zero","false"};
        String[] line2 = {"1","Two plus two?","2","four","true"};
        String[] line3 = {"1","Two plus two?","3","five","false"};
        String[] line4 = {"2","What language uses Spring framework?","4","Java","true"};
        String[] line5 = {"2","What language uses Spring framework?","5","JavaScript","false"};
        String[] line6 = {"2","What language uses Spring framework?","6","Python","false"};

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