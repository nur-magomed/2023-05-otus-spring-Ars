package edu.nur.util;

import edu.nur.model.Question;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("QuestionConverterCsvImpl class")
class QuestionConverterCsvImplTest {

    static List<Question> questions = new ArrayList<>();

    static List<String[]> lines = new ArrayList<>();

    static Question question1 = null;

    static Question question2 = null;

    @BeforeAll
    static void setUp() {

        question1 = new Question(1, "Two plus two?", new HashSet<>(), 2);
        question2 = new Question(2, "What language uses Spring framework?", new HashSet<>(), 1);
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

    @DisplayName("String array converted to question correctly")
    @Test
    void convertToQuestionTest() {

        QuestionConverterCsvImpl converter = QuestionConverterCsvImpl.getInstance();
        List<Question> questions = converter.convertToQuestion(lines);
        assertEquals(questions.get(0).getId(), question1.getId(), "converter questions id is incorrect");
        assertEquals(questions.get(0).getTitle(), question1.getTitle(), "converter questions title is incorrect");
        assertEquals(questions.get(0).getCorrectAnswerId(), question1.getCorrectAnswerId(), "converter questions corrected answer is incorrect");

        assertEquals(questions.get(1).getId(), question2.getId(), "converter questions id is incorrect");
        assertEquals(questions.get(1).getTitle(), question2.getTitle(), "converter questions title is incorrect");
        assertEquals(questions.get(1).getCorrectAnswerId(), question2.getCorrectAnswerId(), "converter questions corrected answer is incorrect");

    }
}