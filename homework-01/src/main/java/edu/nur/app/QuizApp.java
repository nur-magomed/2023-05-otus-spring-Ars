package edu.nur.app;

import edu.nur.io.Printer;
import edu.nur.io.PrinterSystemOutImpl;
import edu.nur.service.QuestionService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuizApp {

    public static void run() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService questionService = context.getBean(QuestionService.class);
        Printer printer = new PrinterSystemOutImpl();
        printer.printQuestions(questionService.getQuestions());

    }

}
