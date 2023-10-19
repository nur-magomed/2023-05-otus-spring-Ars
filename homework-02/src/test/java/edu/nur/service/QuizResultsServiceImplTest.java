package edu.nur.service;

import edu.nur.model.Answer;
import edu.nur.model.Question;
import edu.nur.model.QuizResults;
import edu.nur.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("QuizResultsServiceImpl class")
class QuizResultsServiceImplTest {

    private Question question1;

    private Question question2;

    private Question question3;

    private Student student = new Student("FirstName", "LastName");


    private final Map<Long, Answer> userAnswersOneCorrect = new HashMap<>();
    private final Map<Long, Answer> userAnswersTwoCorrect = new HashMap<>();

    private final QuizResults quizResultsOneCorrect = new QuizResults(student, userAnswersOneCorrect);

    private final QuizResults quizResultsTwoCorrect = new QuizResults(student, userAnswersTwoCorrect);

    private final QuizResultsService quizResultsService = new QuizResultsServiceImpl(2);

    @BeforeEach
    void setUp() {
        userAnswersOneCorrect.clear();
        userAnswersTwoCorrect.clear();

        Answer q1answer1 = new Answer(1, "zero", false);
        Answer q1answer2Correct = new Answer(2, "four", true);
        Answer q1answer3 = new Answer(3, "five", false);

        Answer q2answer1Correct = new Answer(4, "Java", true);
        Answer q2answer2 = new Answer(5, "JavaScript", false);
        Answer q2answer3 = new Answer(6, "Python", false);

        Answer q3answer1 = new Answer(7, "Malaysia", false);
        Answer q3answer2Correct = new Answer(8, "Indonesia", true);
        Answer q3answer3 = new Answer(9, "India", false);

        question1 = new Question(1, "Two plus two?", new HashSet<>(Arrays.asList(q1answer1, q1answer2Correct, q1answer3)));
        question2 = new Question(2, "What language uses Spring framework?", new HashSet<>(Arrays.asList(q2answer1Correct, q2answer2, q2answer3)));
        question3 = new Question(3, "In which country is Java Island?", new HashSet<>(Arrays.asList(q3answer1, q3answer2Correct, q3answer3)));

        userAnswersOneCorrect.put(question1.getId(), q1answer2Correct);
        userAnswersOneCorrect.put(question2.getId(), q2answer2);
        userAnswersOneCorrect.put(question3.getId(), q3answer1);

        userAnswersTwoCorrect.put(question1.getId(), q1answer2Correct);
        userAnswersTwoCorrect.put(question2.getId(), q2answer1Correct);
        userAnswersTwoCorrect.put(question3.getId(), q1answer3);

    }

    @DisplayName("get correct answers count")
    @Test
    void correctAnswersCount() {

        assertEquals(1, quizResultsService.correctAnswersCount(quizResultsOneCorrect), "correctAnswersCount is not correct");
        assertEquals(2, quizResultsService.correctAnswersCount(quizResultsTwoCorrect), "correctAnswersCount is not correct");
        //assertTrue();
    }

    @DisplayName("is passed")
    @Test
    void isPassed() {
        assertFalse(quizResultsService.isPassed(quizResultsOneCorrect), "isPassed is not correct");
        assertTrue(quizResultsService.isPassed(quizResultsTwoCorrect), "isPasssed is not correct");
    }
}