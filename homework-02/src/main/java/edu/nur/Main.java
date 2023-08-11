package edu.nur;

import edu.nur.io.QuestionPrinter;
import edu.nur.service.QuizRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizRunner printer = context.getBean(QuizRunner.class);
        printer.runQuiz();
    }
}