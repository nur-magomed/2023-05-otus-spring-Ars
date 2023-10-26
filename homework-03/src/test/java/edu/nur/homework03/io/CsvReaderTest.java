package edu.nur.homework03.io;

import edu.nur.homework03.config.AppProps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CsvReader class")
@EnableConfigurationProperties(value = AppProps.class)
@TestPropertySource("classpath:application.yml")
@SpringBootTest(properties = {"quiz.runner.enabled=false"})
class CsvReaderTest {

    @Value("${quiz-app.file-csv-path}")
    private String FILE_NAME;

    @DisplayName("readAllLines method works as expected")
    @Test
    void readAllLinesTest() throws Exception {
        String[] line1 = {"1","Two plus two?","1","zero","false"};
        String[] line2 = {"1","Two plus two?","2","four","true"};
        String[] line3 = {"1","Two plus two?","3","five","false"};
        String[] line4 = {"2","What language uses Spring framework?","4","Java","true"};
        String[] line5 = {"2","What language uses Spring framework?","5","JavaScript","false"};
        String[] line6 = {"2","What language uses Spring framework?","6","Python","false"};

        CsvReader reader = new CsvReader(FILE_NAME);
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