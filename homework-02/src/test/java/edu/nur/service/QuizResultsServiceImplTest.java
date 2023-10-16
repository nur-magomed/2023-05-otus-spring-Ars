package edu.nur.service;

import edu.nur.model.Answer;
import edu.nur.model.Question;
import edu.nur.model.QuizResults;
import edu.nur.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

class QuizResultsServiceImplTest {

    private Question question1;

    private Question question2;

    private Question question3;

    Student student = new Student("FirstName", "LastName");
    private QuizResults quizResults;

    Map<Long, Answer> userAnswersOneCorrect = new HashMap<>();
    Map<Long, Answer> userAnswersTwoCorrect = new HashMap<>();



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
        question3 = new Question(2, "In which country is Java Island?", new HashSet<>(Arrays.asList(q3answer1, q3answer2Correct, q3answer3)));

        userAnswersOneCorrect.put(question1.getId(), q1answer2Correct);
        userAnswersOneCorrect.put(question2.getId(), q2answer2);
        userAnswersOneCorrect.put(question3.getId(), q3answer1);

        userAnswersTwoCorrect.put(question1.getId(), q1answer2Correct);
        userAnswersTwoCorrect.put(question2.getId(), q2answer1Correct);
        userAnswersTwoCorrect.put(question3.getId(), q1answer3);

    }

    @Test
    void correctAnswersCount() {

        //assertTrue();
    }

    @Test
    void isPassed() {
    }
}