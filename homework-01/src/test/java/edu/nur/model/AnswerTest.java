package edu.nur.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Answer class")
class AnswerTest {

    @DisplayName("constructor works as expected")
    @Test
    void constructorTest() {
        long answerId = 1L;
        String answerTitle = "Answer test title";
        Answer answer = new Answer(answerId, answerTitle, true);

        assertEquals(answerId, answer.getId(), "incorrect answer id");
        assertEquals(answerTitle, answer.getTitle(), "incorrect answer title");
        assertTrue(answer.isCorrect());
    }

}