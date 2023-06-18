package edu.nur.io;

import edu.nur.model.Question;

import java.util.List;

public interface Printer {

    void print(Object obj);

    void printQuestion(Question question);

    void printQuestions(List<Question> questions);
}
