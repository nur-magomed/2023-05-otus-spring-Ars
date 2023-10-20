package edu.nur.homework03.dao;

import edu.nur.homework03.io.Reader;
import edu.nur.homework03.model.Question;
import edu.nur.homework03.util.QuestionConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.mock;

@DisplayName("QuestionDaoCsvImpl class")
@SpringBootTest(properties = {"quiz.runner.enabled=false"})
class QuestionDaoCsvTest {

    private final List<Question> questions = new ArrayList<>();

    private final List<String[]> lines = new ArrayList<>();

    @BeforeEach
    void setUp() {
        questions.clear();
        lines.clear();

        Question question1 = new Question(1, "Two plus two?", new HashSet<>());
        Question question2 = new Question(2, "What language uses Spring framework?", new HashSet<>());
        questions.add(question1);
        questions.add(question2);


        String[] line1 = {"1","Two plus two?","1","zero","false"};
        String[] line2 = {"1","Two plus two?","2","four","true"};
        String[] line3 = {"1","Two plus two?","3","five","false"};
        String[] line4 = {"2","What language uses Spring framework?","4","Java","true"};
        String[] line5 = {"2","What language uses Spring framework?","5","JavaScript","false"};
        String[] line6 = {"2","What language uses Spring framework?","6","Python","false"};
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);
        lines.add(line5);
        lines.add(line6);

    }

    @DisplayName("get questions method works correctly")
    @Test
    void getQuestionsTest() {

        Reader readerMock = mock(Reader.class);
        QuestionConverter converterMock = mock(QuestionConverter.class);
        Mockito.when(readerMock.readAllLines()).thenReturn(lines);
        Mockito.when(converterMock.convertToQuestions(readerMock.readAllLines())).thenReturn(questions);

        QuestionDaoCsv questionConverterCsv = new QuestionDaoCsv(readerMock, converterMock);

        List<Question> questionsResult = questionConverterCsv.getQuestions();
        assertArrayEquals(questions.toArray(), questionsResult.toArray(), "getQuestions method is incorrect");

    }
}