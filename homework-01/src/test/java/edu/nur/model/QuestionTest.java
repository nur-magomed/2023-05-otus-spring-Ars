package edu.nur.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Question class")
class QuestionTest {

    @DisplayName("constructor works as expected")
    @Test
    void constructorTest() {
        long questionId = 1L;
        String questionTitle = "Question test title";

        Question question = new Question(questionId, questionTitle, Collections.emptySet());

        assertEquals(questionId, question.getId(), "incorrect question id");
        assertEquals(questionTitle, question.getTitle(), "incorrect question title");
        assertTrue(question.getAnswers().isEmpty(), "incorrect answers set");
    }
}