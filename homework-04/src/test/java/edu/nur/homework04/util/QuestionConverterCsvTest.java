package edu.nur.homework04.util;

import edu.nur.homework04.model.Answer;
import edu.nur.homework04.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("QuestionConverterCsv class")
@SpringBootTest(properties = {"quiz.runner.enabled=false"})
class QuestionConverterCsvTest {

    private final List<String[]> lines = new ArrayList<>();
    private final Map<Long, Answer> answersMap = new HashMap<>();

    private Question question1;

    private Question question2;

    @Autowired
    private QuestionConverterCsv questionConverterCsv;

    @BeforeEach
    void setUp() {
        lines.clear();
        answersMap.clear();

        Answer q1answer1 = new Answer(1, "zero", false);
        Answer q1answer2 = new Answer(2, "four", true);
        Answer q1answer3 = new Answer(3, "five", false);

        Answer q2answer1 = new Answer(4, "Java", true);
        Answer q2answer2 = new Answer(5, "JavaScript", false);
        Answer q2answer3 = new Answer(6, "Python", false);

        answersMap.put(q1answer1.getId(), q1answer1);
        answersMap.put(q1answer2.getId(), q1answer2);
        answersMap.put(q1answer3.getId(), q1answer3);
        answersMap.put(q2answer1.getId(), q2answer1);
        answersMap.put(q2answer2.getId(), q2answer2);
        answersMap.put(q2answer3.getId(), q2answer3);

        question1 = new Question(1, "Two plus two?", new HashSet<>(Arrays.asList(q1answer1, q1answer2, q1answer3)));
        question2 = new Question(2, "What language uses Spring framework?", new HashSet<>(Arrays.asList(q2answer1, q2answer2, q2answer3)));

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

    @DisplayName("String array converted to question correctly")
    @Test
    void convertToQuestionsTest() {

        List<Question> questions = questionConverterCsv.convertToQuestions(lines);
        Question questionOne = questions.get(0);
        assertEquals(questionOne.getId(), question1.getId(), "converter questions id is incorrect");
        assertEquals(questionOne.getTitle(), question1.getTitle(), "converter questions title is incorrect");
        for (Answer a: questionOne.getAnswers()) {
            Answer targetAnswer = answersMap.get(a.getId());
            assertEquals(targetAnswer.getId(), a.getId(), "converted answers id is incorrect");
            assertEquals(targetAnswer.getTitle(), a.getTitle(), "converted answers title is incorrect");
            assertEquals(targetAnswer.isCorrect(), a.isCorrect(), "converted answers isCorrect is incorrect");
        }

        Question questionTwo = questions.get(1);
        assertEquals(questionTwo.getId(), question2.getId(), "converter questions id is incorrect");
        assertEquals(questionTwo.getTitle(), question2.getTitle(), "converter questions title is incorrect");
        for (Answer a: questionTwo.getAnswers()) {
            Answer targetAnswer = answersMap.get(a.getId());
            assertEquals(targetAnswer.getId(), a.getId(), "converted answers id is incorrect");
            assertEquals(targetAnswer.getTitle(), a.getTitle(), "converted answers title is incorrect");
            assertEquals(targetAnswer.isCorrect(), a.isCorrect(), "converted answers isCorrect is incorrect");
        }

    }
}