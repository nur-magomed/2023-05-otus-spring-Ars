package edu.nur.io;

import edu.nur.model.Answer;
import edu.nur.model.Question;

import java.util.List;

public class PrinterSystemOutImpl implements Printer {

    @Override
    public void print(Object obj) {
        System.out.println(obj);
    }

    @Override
    public void printQuestion(Question question) {
        print(question.getTitle());
        for (Answer a: question.getAnswers()) {
            print(a.getTitle());
        }
    }

    @Override
    public void printQuestions(List<Question> questions) {
        for (Question question: questions) {
            printQuestion(question);
        }
    }


}
