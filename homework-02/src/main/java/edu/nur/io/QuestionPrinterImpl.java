package edu.nur.io;

import edu.nur.model.Answer;
import edu.nur.model.Question;
import edu.nur.service.QuestionService;
import org.springframework.stereotype.Service;

@Service
public class QuestionPrinterImpl implements QuestionPrinter {

    private final InputOutputService outputService;

    private final QuestionService questionService;

    public QuestionPrinterImpl(InputOutputService outputService, QuestionService questionService) {
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

    @Override
    public void printQuestion(Question question) {
        printStrLn(question.getTitle());
        for (Answer a : question.getAnswers()) {
            printStrLn(a.getTitle());
        }

    }

}
