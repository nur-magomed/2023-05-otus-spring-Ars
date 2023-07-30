package edu.nur.io;

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

    @Override
    public void printQuestions() {
        for (Question question : questionService.getQuestions()) {
            printQuestion(question);
        }
    }

    private void printStrLn(String str) {
        outputService.outputString(str);
    }

    private void printQuestion(Question question) {
        printStrLn(question.getTitle());
        for (Answer a : question.getAnswers()) {
            printStrLn(a.getTitle());
        }
    }

}
