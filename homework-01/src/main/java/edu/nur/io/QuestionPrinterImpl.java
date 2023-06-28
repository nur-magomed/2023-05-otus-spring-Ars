package edu.nur.io;

import edu.nur.exception.ReaderException;
import edu.nur.model.Answer;
import edu.nur.model.Question;
import edu.nur.service.QuestionService;

public class QuestionPrinterImpl implements QuestionPrinter {

    private final OutputService outputService;

    private final QuestionService questionService;

    public QuestionPrinterImpl(OutputService outputService, QuestionService questionService) {
        this.outputService = outputService;
        this.questionService = questionService;
    }


    public void print(String str) {
        outputService.outputString(str);
    }


    public void printQuestion(Question question) {
        print(question.getTitle());
        for (Answer a: question.getAnswers()) {
            print(a.getTitle());
        }
    }

    @Override
    public void printQuestions() throws ReaderException {
        for (Question question: questionService.getQuestions()) {
            printQuestion(question);
        }
    }

}
