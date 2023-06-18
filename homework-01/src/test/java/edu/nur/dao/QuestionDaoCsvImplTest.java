package edu.nur.dao;

import edu.nur.io.Reader;
import edu.nur.model.Question;
import edu.nur.util.QuestionConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.mock;

@DisplayName("QuestionDaoCsvImpl class")
class QuestionDaoCsvImplTest {

    static List<Question> questions = new ArrayList<>();

    static List<String[]> lines = new ArrayList<>();

    @BeforeAll
    static void setUp() {

        Question question1 = new Question(1, "Two plus two?", new HashSet<>(), 2);
        Question question2 = new Question(2, "What language uses Spring framework?", new HashSet<>(), 1);
        questions.add(question1);
        questions.add(question2);


        String[] line1 = {"1","Two plus two?","2","1","zero"};
        String[] line2 = {"1","Two plus two?","2","2","four"};
        String[] line3 = {"1","Two plus two?","2","3","five"};
        String[] line4 = {"2","What language uses Spring framework?","1","4","Java"};
        String[] line5 = {"2","What language uses Spring framework?","1","5","JavaScript"};
        String[] line6 = {"2","What language uses Spring framework?","1","6","Python"};
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
        Mockito.when(converterMock.convertToQuestion(readerMock.readAllLines())).thenReturn(questions);

        QuestionDaoCsvImpl questionConverterCsv = new QuestionDaoCsvImpl(readerMock, converterMock);

        List<Question> questionsResult = questionConverterCsv.getQuestions();
        assertArrayEquals(questions.toArray(), questionsResult.toArray(), "getQuestions method is incorrect");

    }
}